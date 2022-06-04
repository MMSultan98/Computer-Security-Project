package blockchain;

import crypto.SHA;

public class Blockchain {
    
    private BlockHashPointer head;


    public Blockchain() {
        Block genesisBlock = new Block(null, null, null);
        byte[] genesisBlockHash;
        try {
            genesisBlockHash = SHA.generateHash(genesisBlock);
        } catch (Exception e) {
            System.err.println("Could not generate block hash.");   
            e.printStackTrace();
            return;
        }
        this.head = new BlockHashPointer(genesisBlock, genesisBlockHash);
    }


    public BlockHashPointer getHead() {
        return this.head;
    }

    public void setHead(BlockHashPointer head) {
        this.head = head;
    }

}
