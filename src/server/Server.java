package server;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.crypto.SecretKey;
import blockchain.Block;
import blockchain.BlockBody;
import blockchain.BlockHashPointer;
import blockchain.Blockchain;
import crypto.AES;
import crypto.DSA;
import crypto.SHA;
import transactions.Transaction;
import transactions.TransactionBody;
import transactions.TransactionClient;
import transactions.TransactionHashPointer;
import transactions.TransactionServer;

public class Server {

    private static final int BLOCK_NUM_TRANSACTIONS = 3;

    private int currentPatientID;
    private int currentTransactionID;
    private int currentBlockID;
    private KeyPair keyPair;
    private Blockchain blockchain;
    private HashMap<Integer, Patient> patients;
    private HashMap<Integer, Doctor> doctors;
    private ArrayList<Transaction> pendingTransactions;
    private HashSet<Patient> pendingPatients;

    public Server() {
        this.currentPatientID = 0;
        this.currentTransactionID = 0;
        this.currentBlockID = 0;
        try {
            this.keyPair = DSA.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Could not generate key pair for the server.");
            e.printStackTrace();
        }
        this.blockchain = new Blockchain();
        this.patients = new HashMap<Integer, Patient>();
        this.doctors = new HashMap<Integer, Doctor>();
        this.pendingTransactions = new ArrayList<Transaction>();
        this.pendingPatients = new HashSet<Patient>();
    }

    public PublicKey getPublicKey() {
        return this.keyPair.getPublic();
    }

    
    public boolean createPatient() {
        SecretKey symmetricKey;
        try {
            symmetricKey = AES.generateKey();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Could not generate symmetric key for the patient.");
            e.printStackTrace();
            return false;
        }
        Patient newPatient = new Patient(++currentPatientID, symmetricKey);
        this.patients.put(newPatient.getPatientID(), newPatient);
        return true;
    }

    public boolean addDoctor(int doctorID, PublicKey doctorPublicKey) {
        Doctor newDoctor = new Doctor(doctorID, doctorPublicKey);
        this.doctors.put(doctorID, newDoctor);
        return true;
    }

    public SecretKey assignPatientToDoctor(int patientID, int doctorID) {
        Patient patient = patients.get(patientID);
        Doctor doctor = doctors.get(doctorID);
        if (patient == null) {
            System.err.println("Invalid patient id.");
            return null;
        }
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return null;
        }
        doctor.addPatient(patientID);
        return patient.getSymmetricKey();
    }

    public boolean receiveTransaction(TransactionClient transactionClient) {
        Doctor doctor = this.doctors.get(transactionClient.getTransactionHeader().getDoctorID());
        Patient patient = this.patients.get(transactionClient.getTransactionHeader().getPatientID());
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return false;
        }
        if (patient == null) {
            System.err.println("Invalid patient id.");
            return false;
        }

        boolean verified;
        try {
            HashSet<Object> set = new HashSet<Object>();
            set.add(transactionClient.getTransactionHeader());
            set.add(transactionClient.getEncryptedTransactionBody());
            verified = DSA.verifyObject(set, transactionClient.getTransactionSignature(), doctor.getPublicKey());
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | IOException e) {
            System.err.println("Could not verify transaction.");
            e.printStackTrace();
            return false;
        }
        if (!verified) {
            System.err.println("Invalid transaction signature.");
            return false;
        }

        if (!doctor.getPatients().contains(patient.getPatientID())) {
            System.err.println("Unauthorized doctor patient id.");
            return false;
        }

        boolean isPatientInfo = transactionClient.getTransactionHeader()
                .getTransactionType() == TransactionBody.PATIENT_INFO;
        if (patient.getLastTransaction() == null && !isPatientInfo) {
            System.err.println("First transaction must be patient info.");
            return false;
        }

        TransactionHashPointer previousTransactionHashPointer = patient.getLastTransaction();
        TransactionServer transactionServer = new TransactionServer(this.currentTransactionID++,
                transactionClient.getTransactionHeader(), transactionClient.getEncryptedTransactionBody(),
                transactionClient.getTransactionSignature());
        byte[] transactionSignature;
        try {
            HashSet<Object> set = new HashSet<Object>();
            set.add(previousTransactionHashPointer);
            set.add(transactionServer);
            transactionSignature = DSA.signObject(set, this.keyPair.getPrivate());
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | IOException e) {
            System.err.println("Could not sign the transaction.");
            e.printStackTrace();
            return false;
        }

        Transaction newTransaction = new Transaction(previousTransactionHashPointer, transactionServer,
                transactionSignature);
        byte[] newTransactionHash;
        try {
            newTransactionHash = SHA.generateHash(newTransaction);
        } catch (NoSuchAlgorithmException | IOException e) {
            System.err.println("Could not hash the transaction.");
            e.printStackTrace();
            return false;
        }
        TransactionHashPointer newTransactionHashPointer = new TransactionHashPointer(newTransaction, newTransactionHash);
        patient.setLastTransaction(newTransactionHashPointer);
        this.pendingTransactions.add(newTransaction);
        this.pendingPatients.add(patient);
        if (this.pendingTransactions.size() == BLOCK_NUM_TRANSACTIONS) {
            publishBlock();
        }
        return true;
    }

    public ArrayList<Transaction> getLastPatientTransaction(int doctorID, int patientID) {
        if (!verifyTransactionsRequest(doctorID, patientID)) {
            return null;
        }
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        Patient patient = this.patients.get(patientID);
        TransactionHashPointer transactionHashPointer = patient.getLastPublishedTransaction();
        if (transactionHashPointer == null) {
            return transactions;
        }
        if (!verifyTransactionHash(transactionHashPointer)) {
            return null;
        }
        Transaction transaction = transactionHashPointer.getPointer();
        transactions.add(transaction);
        return transactions;
    }

    public ArrayList<Transaction> getAllPatientTransactions(int doctorID, int patientID) {
        if (!verifyTransactionsRequest(doctorID, patientID)) {
            return null;
        }
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        Patient patient = this.patients.get(patientID);
        TransactionHashPointer transactionHashPointer = patient.getLastPublishedTransaction();
        while (transactionHashPointer != null) {
            if (!verifyTransactionHash(transactionHashPointer)) {
                return null;
            }
            Transaction transaction = transactionHashPointer.getPointer();
            transactions.add(transaction);
            transactionHashPointer = transaction.getPreviousTransaction();
        }
        return transactions;
    }

    public ArrayList<Transaction> getPatientTransactionsQuery(int doctorID, int patientID) {
        if (!verifyTransactionsRequest(doctorID, patientID)) {
            return null;
        }
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        Patient patient = this.patients.get(patientID);
        TransactionHashPointer transactionHashPointer = patient.getLastPublishedTransaction();
        while (transactionHashPointer != null) {
            if (!verifyTransactionHash(transactionHashPointer)) {
                return null;
            }
            Transaction transaction = transactionHashPointer.getPointer();
            if (checkTransactionQuery(transaction)) {
                transactions.add(transaction);
            }
            transactionHashPointer = transaction.getPreviousTransaction();
        }
        return transactions;
    }

    
    private void publishBlock() {
        BlockHashPointer previousBlockHashPointer = this.blockchain.getHead();
        BlockBody blockBody = new BlockBody(this.currentBlockID++, this.pendingTransactions);
        byte[] blockBodySignature;
        try {
            HashSet<Object> set = new HashSet<Object>();
            set.add(previousBlockHashPointer);
            set.add(blockBody);
            blockBodySignature = DSA.signObject(set, this.keyPair.getPrivate());
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | IOException e) {
            System.err.println("Could not sign the block.");
            e.printStackTrace();
            return;
        }
        Block newBlock = new Block(previousBlockHashPointer, blockBody, blockBodySignature);
        byte[] newBlockHash;
        try {
            newBlockHash = SHA.generateHash(newBlock);
        } catch (NoSuchAlgorithmException | IOException e) {
            System.err.println("Could not hash the block.");
            e.printStackTrace();
            return;
        }
        for (Patient p : pendingPatients) {
            p.setLastPublishedTransaction(p.getLastTransaction());
        }
        BlockHashPointer newBlockHashPointer = new BlockHashPointer(newBlock, newBlockHash);
        this.blockchain.setHead(newBlockHashPointer);
        this.pendingTransactions = new ArrayList<Transaction>();
        this.pendingPatients = new HashSet<Patient>();
    }

    private boolean verifyTransactionsRequest(int doctorID, int patientID) {
        Doctor doctor = this.doctors.get(doctorID);
        Patient patient = this.patients.get(patientID);
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return false;
        }
        if (patient == null) {
            System.err.println("Invalid patient id.");
            return false;
        }
        if (!doctor.getPatients().contains(patient.getPatientID())) {
            System.err.println("Unauthorized doctor patient id.");
            return false;
        }
        return true;
    }

    private boolean verifyTransactionHash(TransactionHashPointer transactionHashPointer) {
        boolean verified;
        try {
            verified = SHA.verifyHash(transactionHashPointer.getPointer(), transactionHashPointer.getHash());
        } catch (NoSuchAlgorithmException | IOException e) {
            System.err.println("Could not verify transaction hash.");
            e.printStackTrace();
            return false;
        }
        if (!verified) {
            System.err.println("Invalid transaction hash transaction.");
        }
        return true;
    }

    private boolean checkTransactionQuery(Transaction transaction) {
        // TODO
        return true;
    }

}
