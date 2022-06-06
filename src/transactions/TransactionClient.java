package transactions;

import java.io.Serializable;
import javax.crypto.SealedObject;

public class TransactionClient implements Serializable {
    
    private TransactionHeader transactionHeader;
    private SealedObject encrypedTransactionBody;
    private byte[] transactionSignature;


    public TransactionClient(TransactionHeader transactionHeader, SealedObject encrypedTransactionBody, byte[] transactionSignature) {
        this.transactionHeader = transactionHeader;
        this.encrypedTransactionBody = encrypedTransactionBody;
        this.transactionSignature = transactionSignature;
    }


    public TransactionHeader getTransactionHeader() {
        return this.transactionHeader;
    }

    public SealedObject getEncrypedTransactionBody() {
        return this.encrypedTransactionBody;
    }

    public byte[] getTransactionSignature() {
        return this.transactionSignature;
    }
 
}
