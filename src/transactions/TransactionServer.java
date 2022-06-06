package transactions;

import java.io.Serializable;
import javax.crypto.SealedObject;

public class TransactionServer implements Serializable {
    
    private int transactionID;
    private TransactionHeader transactionHeader;
    private SealedObject encrypedTransactionBody;
    private byte[] transactionClientSignature;


    public TransactionServer(int transactionID, TransactionHeader transactionHeader, SealedObject encrypedTransactionBody, byte[] transactionClientSignature) {
        this.transactionID = transactionID;
        this.transactionHeader = transactionHeader;
        this.encrypedTransactionBody = encrypedTransactionBody;
        this.transactionClientSignature = transactionClientSignature;
    }


    public int getTransactionID() {
        return this.transactionID;
    }

    public TransactionHeader getTransactionHeader() {
        return this.transactionHeader;
    }

    public SealedObject getEncrypedTransactionBody() {
        return this.encrypedTransactionBody;
    }

    public byte[] getTransactionClientSignature() {
        return this.transactionClientSignature;
    }
    
}
