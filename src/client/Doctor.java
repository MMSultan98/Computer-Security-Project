package client;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.HashMap;
import javax.crypto.SecretKey;
import crypto.DSA;

public class Doctor {

    private int doctorID;
    private KeyPair keyPair;
    private PublicKey serverPublicKey;
    private HashMap<Integer, Patient> patients;

    
    public Doctor(int doctorID, PublicKey serverPublicKey) {
        this.doctorID = doctorID;
        this.serverPublicKey = serverPublicKey;
        try {
            this.keyPair =  DSA.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Could not generate key pair for the doctor.");
            e.printStackTrace();
        }
        this.patients = new HashMap<Integer, Patient>();
    }

    public int getDoctorID() {
        return this.doctorID;
    }

    public PublicKey getPublicKey() {
        return this.keyPair.getPublic();
    }

    public void addPatient(int patientID, SecretKey symmetricKey) {
        Patient newPatient = new Patient(patientID, symmetricKey);
        patients.put(patientID, newPatient);
    }

    public void createTransaction() {
        // TODO
    }
    
    public void createPatientInfoTransaction() {
        // TODO
    }

    public void createVisitTransaction() {
        // TODO
    }

    public void createLabTestTransaction() {
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
