package clients;

import java.security.KeyPair;
import java.security.PublicKey;
import java.util.HashMap;
import javax.crypto.SecretKey;

public class Doctor {

    private int doctorID;
    private KeyPair keyPair;
    private PublicKey serverPublicKey;
    private HashMap<Integer, SecretKey> patients;

    
    public Doctor(int doctorID, KeyPair keyPair, PublicKey serverPublicKey, HashMap<Integer,SecretKey> patients) {
        this.doctorID = doctorID;
        this.keyPair = keyPair;
        this.serverPublicKey = serverPublicKey;
        this.patients = patients;
    }

    public int getDoctorID() {
        return this.doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public KeyPair getKeyPair() {
        return this.keyPair;
    }

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public PublicKey getServerPublicKey() {
        return this.serverPublicKey;
    }

    public void setServerPublicKey(PublicKey serverPublicKey) {
        this.serverPublicKey = serverPublicKey;
    }

    public HashMap<Integer,SecretKey> getPatients() {
        return this.patients;
    }

    public void setPatients(HashMap<Integer,SecretKey> patients) {
        this.patients = patients;
    }

}
