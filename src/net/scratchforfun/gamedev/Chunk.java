package net.scratchforfun.gamedev;

/**
 * Created by Magnus on 05/10/2014.
 */
public class Chunk {

    int CHUNK_X;
    int CHUNK_Y;

    public Chunk(int CHUNK_X, int CHUNK_Y){
        this.CHUNK_X = CHUNK_X;
        this.CHUNK_Y = CHUNK_Y;
    }

    public boolean equals(Object object){
        if(!(object instanceof Chunk))
            return false;

        Chunk chunk = (Chunk) object;
        return CHUNK_X == chunk.CHUNK_X && CHUNK_Y == chunk.CHUNK_Y;
    }

}
