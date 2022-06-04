package server;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import javax.crypto.SecretKey;
import blockchain.Blockchain;
import crypto.AES;
import crypto.DSA;
import transactions.Transaction;

public class Server {
    
    private int currentPatientID;
    private int currentTransactionID;
    private int currentBlockID;
    private KeyPair keyPair;
    private Blockchain blockchain;
    private HashMap<Integer, Patient> patients;
    private HashMap<Integer, Doctor> doctors;
    private ArrayList<Transaction> pendingTransactions;


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
    }


    public PublicKey getPublicKey() {
        return this.keyPair.getPublic();
    }

    public void createPatient() {
        SecretKey symmetricKey;
        try {
            symmetricKey = AES.generateKey();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Could not generate symmetric key for the patient.");
            e.printStackTrace();
            return;
        }
        Patient newPatient = new Patient(++currentPatientID, symmetricKey);
        this.patients.put(newPatient.getPatientID(), newPatient);
    }

    public void addDoctor(int doctorID, PublicKey doctorPublicKey) {
        Doctor newDoctor = new Doctor(doctorID, doctorPublicKey);
        this.doctors.put(doctorID, newDoctor);
    }

    public SecretKey assignPatientToDoctor(int patientID, int doctorID) {
        Patient patient = patients.get(patientID);
        if (patient == null) {
            System.err.println("Invalid patient id.");
            return null;
        }
        Doctor doctor = doctors.get(doctorID);
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return null;
        }
        doctor.addPatient(patientID);
        return patient.getSymmetricKey();
    }

    public void recieveTransaction() {
        // TODO
    }

    public void createBlock() {
        // TODO
    }

    public void getLastPatientTransaction() {
        // TODO
    }
    
    public void getPatientTransactions() {
        // TODO
    }

    public void getPatientTransactionsQuery() {
        // TODO
    }

}
