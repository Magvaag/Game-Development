package net.scratchforfun.gamedev.world;

import net.scratchforfun.gamedev.Game;
import net.scratchforfun.gamedev.entity.Entity;
import net.scratchforfun.gamedev.reference.References;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Scratch on 9/24/2014.
 */
public class Map {

    List<Chunk> loadedChunks = new CopyOnWriteArrayList<Chunk>();
    List<Entity> loadedEntities = new CopyOnWriteArrayList<Entity>();

    public Map(){
        checkChunks();
    }

    public void render(Graphics g){
        for(Chunk chunk : loadedChunks)
            chunk.render(g);

        for(Entity entity : loadedEntities){
            entity.render(g);
        }
    }

    public void tick(){
        checkChunks();

        for(Chunk chunk : loadedChunks)
            chunk.tick();

        for(Entity entity : loadedEntities)
            entity.tick();
    }

    public void spawnEntity(Entity entity, int x, int y){
        entity.position.set(x, y);
        loadedEntities.add(entity);
    }

    public void checkChunks(){
        System.out.println(Game.INSTANCE.player);
        System.out.println(Game.INSTANCE.player.position);

        int player_chunk_x = Game.floor(Game.INSTANCE.player.position.x / (double) References.TILE_AMOUNT_X);
        int player_chunk_y = Game.floor(Game.INSTANCE.player.position.y/(double)References.TILE_AMOUNT_Y);

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
