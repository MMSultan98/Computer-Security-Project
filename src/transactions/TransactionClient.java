package transactions;

import java.io.Serializable;
import javax.crypto.SealedObject;

public class TransactionClient implements Serializable {
    
    private TransactionHeader transactionHeader;
    private SealedObject transactionBody;
    private byte[] transactionHeaderSignature, transactionBodySignature;


    public TransactionClient(TransactionHeader transactionHeader, SealedObject transactionBody, byte[] transactionHeaderSignature, byte[] transactionBodySignature) {
        this.transactionHeader = transactionHeader;
        this.transactionBody = transactionBody;
        this.transactionHeaderSignature = transactionHeaderSignature;
        this.transactionBodySignature = transactionBodySignature;
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
