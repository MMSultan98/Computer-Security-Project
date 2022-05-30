package transactions;

import java.io.Serializable;
import java.util.Date;

public class TransactionHeader implements Serializable {
    
    private int doctorID;
    private int patientID;
    private byte[] iv;
    private Date timestamp;


    public TransactionHeader(int doctorID, int patientID, byte[] iv) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.iv = iv;
        this.timestamp = new Date();
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

    public byte[] getIv() {
        return this.iv;
    }

    public void setIv(byte[] iv) {
        this.iv = iv;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
}
