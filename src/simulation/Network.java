package simulation;

import java.util.HashMap;
import javax.crypto.SecretKey;
import client.Doctor;
import server.Server;

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
        server.createPatient();
    }

    public void createDoctor() {
        int doctorID = ++currentDoctorID;
        Doctor doctor = new Doctor(doctorID, this.server.getPublicKey());
        doctors.put(doctorID, doctor);
        server.addDoctor(doctorID, doctor.getPublicKey());
    }

    public void assignPatientToDoctor(int patientID, int doctorID) {
        Doctor doctor = doctors.get(doctorID);
        if (doctor == null) {
            System.err.println("Invalid doctor id.");
            return;
        }
        SecretKey symmetricKey = server.assignPatientToDoctor(patientID, doctorID);
        if (symmetricKey == null) {
            return;
        }
        doctor.addPatient(patientID, symmetricKey);
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
