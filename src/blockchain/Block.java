package blockchain;

import java.io.Serializable;

public class Block implements Serializable {
    
    private BlockHashPointer previousBlockHashPointer;
    private BlockBody blockBody;
    private byte[] blockBodySignature;


    public Block(BlockHashPointer previousBlockHashPointer, BlockBody blockBody, byte[] blockBodySignature) {
        this.previousBlockHashPointer = previousBlockHashPointer;
        this.blockBody = blockBody;
        this.blockBodySignature = blockBodySignature;
    }


    public BlockHashPointer getPreviousBlockHashPointer() {
        return this.previousBlockHashPointer;
    }

    public BlockBody getBlockBody() {
        return this.blockBody;
    }

    public byte[] getBlockBodySignature() {
        return this.blockBodySignature;
    }
   
}
