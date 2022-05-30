package blocks;

public class BlockHashPointer {
    
    private BlockContainer pointer;
    private byte[] hash;


    public BlockHashPointer(BlockContainer pointer, byte[] hash) {
        this.pointer = pointer;
        this.hash = hash;
    }

    public BlockContainer getPointer() {
        return this.pointer;
    }

    public void setPointer(BlockContainer pointer) {
        this.pointer = pointer;
    }

    public byte[] getHash() {
        return this.hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

}
