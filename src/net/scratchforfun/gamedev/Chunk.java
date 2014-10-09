package net.scratchforfun.gamedev;

import net.scratchforfun.gamedev.reference.References;

/**
 * Created by Magnus on 05/10/2014.
 */
public class Chunk {

    Tile[][] tiles;

    int CHUNK_X;
    int CHUNK_Y;

    public Chunk(int CHUNK_X, int CHUNK_Y){
        this.CHUNK_X = CHUNK_X;
        this.CHUNK_Y = CHUNK_Y;
    }

    public void populate(){
        tiles = new Tile[References.TILE_AMOUNT_X][References.TILE_AMOUNT_Y];

        // Noise generation

        // TODO: Fix
        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles[0].length; y++){
                tiles[x][y] = new Tile(Material.GRASS);
            }
        }
    }

    public boolean equals(Object object){
        if(!(object instanceof Chunk))
            return false;

        Chunk chunk = (Chunk) object;
        return CHUNK_X == chunk.CHUNK_X && CHUNK_Y == chunk.CHUNK_Y;
    }

}
