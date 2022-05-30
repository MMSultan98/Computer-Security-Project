package transactions;

import java.io.Serializable;

public abstract class Transaction implements Serializable {
    
    private int doctorID;
    private int patientID;
    private String date;


    public Transaction(int doctorID, int patientID, String date) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.date = date;
    }    

    public int getDoctorID() {
        return this.doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getPatientID() {
        return this.patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
