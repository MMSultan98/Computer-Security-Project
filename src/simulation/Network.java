package simulation;

import java.util.ArrayList;
import java.util.HashMap;
import javax.crypto.SecretKey;
import client.Doctor;
import server.Server;
import transactions.Transaction;
import transactions.TransactionClient;

public class Network {

    private Server server;
    private int currentDoctorID;
    private HashMap<Integer, Doctor> doctors;

    public Network() {
        this.server = new Server();
        this.currentDoctorID = 0;
        this.doctors = new HashMap<Integer, Doctor>();
    }

    public void createPatient() {
        boolean success = this.server.createPatient();
        if (success) {
            System.out.println("Patient created successfully.");
        }
    }

    public void createDoctor() {
        Doctor doctor = new Doctor(++this.currentDoctorID, this.server.getPublicKey());
        doctors.put(doctor.getDoctorID(), doctor);
        boolean success = server.addDoctor(doctor.getDoctorID(), doctor.getPublicKey());
        if (success) {
            System.out.println("Doctor created successfully.");
        }
    }

    public void assignPatientToDoctor(int patientID, int doctorID) {
        Doctor doctor = this.doctors.get(doctorID);
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return;
        }
        SecretKey symmetricKey = this.server.assignPatientToDoctor(patientID, doctorID);
        if (symmetricKey == null) {
            return;
        }
        boolean success = doctor.addPatient(patientID, symmetricKey);
        if (success) {
            System.out.println("Patient assigned to doctor successfully.");
        }
    }

    public void createPatientInfoTransaction(int doctorID, int patientID, String name, String age,
            String weight, String height, String sex, HashMap<String, String> initialMeasurements) {
        Doctor doctor = this.doctors.get(doctorID);
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return;
        }
        TransactionClient transactionClient = doctor.createPatientInfoTransaction(patientID, name, age, weight, height,
                sex, initialMeasurements);
        if (transactionClient == null) {
            System.err.println("Could not create transaction at client.");
            return;
        }
        boolean success = this.server.receiveTransaction(transactionClient);
        if (success) {
            System.out.println("Transaction created and received successfully.");
        }
    }

    public void createVisitTransaction(int doctorID, int patientID, String reason, String diagnosis,
            HashMap<String, String> measurements, HashMap<String, String> prescription) {
        Doctor doctor = this.doctors.get(doctorID);
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return;
        }
        TransactionClient transactionClient = doctor.createVisitTransaction(patientID, reason, diagnosis, measurements,
                prescription);
        if (transactionClient == null) {
            System.err.println("Could not create transaction at client.");
            return;
        }
        boolean success = this.server.receiveTransaction(transactionClient);
        if (success) {
            System.out.println("Transaction created and received successfully.");
        }
    }

    public void createLabTestTransaction(int doctorID, int patientID, String testName,
            HashMap<String, String> results) {
        Doctor doctor = this.doctors.get(doctorID);
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return;
        }
        TransactionClient transactionClient = doctor.createLabTestTransaction(patientID, testName, results);
        if (transactionClient == null) {
            System.err.println("Could not create transaction at client.");
            return;
        }
        boolean success = this.server.receiveTransaction(transactionClient);
        if (success) {
            System.out.println("Transaction created and received successfully.");
        }
    }

    public void getLastPatientTransaction(int doctorID, int patientID) {
        Doctor doctor = this.doctors.get(doctorID);
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return;
        }
        if (!doctor.getLastPatientTransaction(patientID)) {
            return;
        }
        ArrayList<Transaction> transactions = this.server.getLastPatientTransaction(doctorID, patientID);
        if (transactions == null) {
            System.err.println("Could not retrieve transactions from server.");
            return;
        }
        boolean success = doctor.receiveTransactions(transactions);
        if (success) {
            System.out.println(transactions.size() + " transaction(s) received successfully.");
        }
    }

    public void getAllPatientTransactions(int doctorID, int patientID) {
        Doctor doctor = this.doctors.get(doctorID);
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return;
        }
        if (!doctor.getLastPatientTransaction(patientID)) {
            return;
        }
        ArrayList<Transaction> transactions = this.server.getAllPatientTransactions(doctorID, patientID);
        if (transactions == null) {
            System.err.println("Could not retrieve transactions from server.");
            return;
        }
        boolean success = doctor.receiveTransactions(transactions);
        if (success) {
            System.out.println(transactions.size() + " transaction(s) received successfully.");
        }
    }

    public void getPatientTransactionsQuery(int doctorID, int patientID) {
        Doctor doctor = this.doctors.get(doctorID);
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return;
        }
        if (!doctor.getLastPatientTransaction(patientID)) {
            return;
        }
        ArrayList<Transaction> transactions = this.server.getPatientTransactionsQuery(doctorID, patientID);
        if (transactions == null) {
            System.err.println("Could not retrieve transactions from server.");
            return;
        }
        boolean success = doctor.receiveTransactions(transactions);
        if (success) {
            System.out.println(transactions.size() + " transaction(s) received successfully.");
        }
    }

}
