package transactions;

import javax.crypto.SealedObject;

public class TransactionContainer {
    
    private TransactionHashPointer previousTransaction;
    private TransactionHeader header;
    private byte[] headerSignature;
    private SealedObject encryptedTransaction;
    private byte[] transactionSignature;


    public TransactionContainer(TransactionHashPointer previousTransaction, TransactionHeader header, byte[] headerSignature, SealedObject encryptedTransaction, byte[] transactionSignature) {
        this.previousTransaction = previousTransaction;
        this.header = header;
        this.headerSignature = headerSignature;
        this.encryptedTransaction = encryptedTransaction;
        this.transactionSignature = transactionSignature;
    }

    public TransactionHashPointer getPreviousTransaction() {
        return this.previousTransaction;
    }

    public void setPreviousTransaction(TransactionHashPointer previousTransaction) {
        this.previousTransaction = previousTransaction;
    }

    public TransactionHeader getHeader() {
        return this.header;
    }

    public void setHeader(TransactionHeader header) {
        this.header = header;
    }

    public byte[] getHeaderSignature() {
        return this.headerSignature;
    }

    public void setHeaderSignature(byte[] headerSignature) {
        this.headerSignature = headerSignature;
    }

    public SealedObject getEncryptedTransaction() {
        return this.encryptedTransaction;
    }

    public void setEncryptedTransaction(SealedObject encryptedTransaction) {
        this.encryptedTransaction = encryptedTransaction;
    }

    public byte[] getTransactionSignature() {
        return this.transactionSignature;
    }

    public void setTransactionSignature(byte[] transactionSignature) {
        this.transactionSignature = transactionSignature;
    }
    
}
