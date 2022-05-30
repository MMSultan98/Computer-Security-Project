package transactions;

public class TransactionHashPointer {
 
    private TransactionContainer pointer;
    private byte[] hash;


    public TransactionHashPointer(TransactionContainer pointer, byte[] hash) {
        this.pointer = pointer;
        this.hash = hash;
    }

    public TransactionContainer getPointer() {
        return this.pointer;
    }

    public void setPointer(TransactionContainer pointer) {
        this.pointer = pointer;
    }

    public byte[] getHash() {
        return this.hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

}
