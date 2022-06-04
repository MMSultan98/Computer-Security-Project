package transactions;

import java.io.Serializable;
import javax.crypto.SealedObject;

public class TransactionServer implements Serializable {
    
    private int transactionID;
    private TransactionHeader transactionHeader;
    private SealedObject transactionBody;
    private byte[] transactionHeaderSignature, transactionBodySignature;


    public TransactionServer(int transactionID, TransactionHeader transactionHeader, SealedObject transactionBody, byte[] transactionHeaderSignature, byte[] transactionBodySignature) {
        this.transactionID = transactionID;
        this.transactionHeader = transactionHeader;
        this.transactionBody = transactionBody;
        this.transactionHeaderSignature = transactionHeaderSignature;
        this.transactionBodySignature = transactionBodySignature;
    }


    public int getTransactionID() {
        return this.transactionID;
    }

    public TransactionHeader getTransactionHeader() {
        return this.transactionHeader;
    }

    public SealedObject getTransactionBody() {
        return this.transactionBody;
    }

    public byte[] getTransactionHeaderSignature() {
        return this.transactionHeaderSignature;
    }

    public byte[] getTransactionBodySignature() {
        return this.transactionBodySignature;
    }
   
}
