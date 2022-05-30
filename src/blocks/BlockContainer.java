package blocks;

public class BlockContainer {
    
    private BlockHashPointer previousBlock;
    private Block block;
    private byte[] blockSignature;


    public BlockContainer(BlockHashPointer previousBlock, Block block, byte[] blockSignature) {
        this.previousBlock = previousBlock;
        this.block = block;
        this.blockSignature = blockSignature;
    }

    public BlockHashPointer getPreviousBlock() {
        return this.previousBlock;
    }

    public void setPreviousBlock(BlockHashPointer previousBlock) {
        this.previousBlock = previousBlock;
    }

    public Block getBlock() {
        return this.block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public byte[] getBlockSignature() {
        return this.blockSignature;
    }

    public void setBlockSignature(byte[] blockSignature) {
        this.blockSignature = blockSignature;
    }    

}
