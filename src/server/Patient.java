package server;

import javax.crypto.SecretKey;
import transactions.TransactionHashPointer;

public class Patient {
    
    private int patientID;
    private SecretKey symmetricKey;
    private TransactionHashPointer lastTransaction;


    public Patient(int patientID, SecretKey symmetricKey) {
        this.patientID = patientID;
        this.symmetricKey = symmetricKey;
        this.lastTransaction = null;
    }

    public int getPatientID() {
        return this.patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public SecretKey getSymmetricKey() {
        return this.symmetricKey;
    }

    public void setSymmetricKey(SecretKey symmetricKey) {
        this.symmetricKey = symmetricKey;
    }

    public TransactionHashPointer getLastTransaction() {
        return this.lastTransaction;
    }

    public void setLastTransaction(TransactionHashPointer lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

}
