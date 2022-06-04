package transactions;

import java.io.Serializable;
import java.util.Date;

public class TransactionHeader implements Serializable {

    private int transactionType, doctorID, patientID;
    private byte[] iv;
    private Date timestamp;


    public TransactionHeader(int transactionType, int doctorID, int patientID, byte[] iv) {
        this.transactionType = transactionType;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.iv = iv;
        this.timestamp = new Date();
    }

    
    public int getTransactionType() {
        return this.transactionType;
    }

    public int getDoctorID() {
        return this.doctorID;
    }

    public int getPatientID() {
        return this.patientID;
    }

    public byte[] getIv() {
        return this.iv;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }
    
}