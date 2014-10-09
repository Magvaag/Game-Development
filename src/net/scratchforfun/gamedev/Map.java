package net.scratchforfun.gamedev;

import net.scratchforfun.gamedev.reference.References;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Scratch on 9/24/2014.
 */
public class Map {

    List<Chunk> loadedChunks = new CopyOnWriteArrayList<Chunk>();
    Player player;

    public Map(Player player){
        this.player = player;
        checkChunks();
    }

    public void checkChunks(){
        int player_chunk_x = player.posX/References.TILE_AMOUNT_X;
        int player_chunk_y = player.posY/References.TILE_AMOUNT_Y;

        // Unload chunks
        unloadChunks(player_chunk_x, player_chunk_y);

        // Load chunks
        loadChunks(player_chunk_x, player_chunk_y);
    }

    private void unloadChunks(int player_chunk_x, int player_chunk_y){
        for(Chunk chunk : loadedChunks){
            if(chunk.CHUNK_X > player_chunk_x+(References.CHUNK_AMOUNT_X-1)/2 || chunk.CHUNK_X < player_chunk_x-(References.CHUNK_AMOUNT_X-1)/2 || chunk.CHUNK_Y > player_chunk_y+(References.CHUNK_AMOUNT_Y-1)/2 || chunk.CHUNK_Y < player_chunk_y-(References.CHUNK_AMOUNT_Y-1)/2)
                loadedChunks.remove(chunk);
        }
    }

    private void loadChunks(int player_chunk_x, int player_chunk_y){
        for(int x = player_chunk_x-(References.CHUNK_AMOUNT_X-1)/2; x <= player_chunk_x+(References.CHUNK_AMOUNT_X-1)/2; x++){
            for(int y = player_chunk_y-(References.CHUNK_AMOUNT_Y-1)/2; y <= player_chunk_y+(References.CHUNK_AMOUNT_Y-1)/2; y++){
                if(!loadedChunks.contains(new Chunk(x, y))) {
                    Chunk chunk = new Chunk(x, y);
                    chunk.populate();
                    loadedChunks.add(chunk);
                }
            }
        }
    }

}
