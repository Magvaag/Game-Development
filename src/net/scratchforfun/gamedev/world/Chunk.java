package net.scratchforfun.gamedev.world;

import net.scratchforfun.gamedev.Game;
import net.scratchforfun.gamedev.reference.References;

import java.awt.*;

import static net.scratchforfun.gamedev.reference.References.*;
import static net.scratchforfun.gamedev.Game.*;
import static net.scratchforfun.gamedev.reference.References.SCREEN_WIDTH;

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

    public void render(Graphics g){
        int posX = ceil((CHUNK_X*TILE_AMOUNT_X*TILE_SIZE*PIXEL_SIZE - Game.INSTANCE.player.position.x*TILE_SIZE*PIXEL_SIZE - TILE_SIZE*PIXEL_SIZE/2)*PIXEL_SCALE_WIDTH + SCREEN_WIDTH/2);
        int posY = ceil((CHUNK_Y*TILE_AMOUNT_Y*TILE_SIZE*PIXEL_SIZE - Game.INSTANCE.player.position.y*TILE_SIZE*PIXEL_SIZE - TILE_SIZE*PIXEL_SIZE/2)*PIXEL_SCALE_HEIGHT + SCREEN_HEIGHT/2);

        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles[0].length; y++){
                tiles[x][y].render(g, ceil(x*TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_WIDTH) + posX, ceil(y*TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_HEIGHT) + posY);
                //g.drawImage(chunk.tiles[x][y].texture, ceil(x*TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_WIDTH) + posX, ceil(y*TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_HEIGHT) + posY, ceil(TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_WIDTH), ceil(TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_HEIGHT), null);
            }
        }
        g.drawLine(posX, 0, posX, SCREEN_HEIGHT);
        g.drawLine(0, posY, SCREEN_WIDTH, posY);
        g.drawString("Chunk("+CHUNK_X+", "+CHUNK_Y+")", ceil(posX), ceil(posY+10));
    }

    public void populate(){
        tiles = new Tile[References.TILE_AMOUNT_X][References.TILE_AMOUNT_Y];

        SimplexNoise simplexNoise = new SimplexNoise(7, 0.1);

        double xStart = CHUNK_X*References.TILE_AMOUNT_X;
        double xEnd = xStart+References.TILE_AMOUNT_X;
        double yStart = CHUNK_Y*References.TILE_AMOUNT_Y;
        double yEnd = yStart+References.TILE_AMOUNT_Y;

        int xResolution = References.TILE_AMOUNT_X;
        int yResolution = References.TILE_AMOUNT_Y;

        double[][] data = new double[xResolution][yResolution];

        for(int i = 0; i < xResolution; i++){
            for(int j = 0; j < yResolution; j++){
                int x = (int)(xStart+(i*(xEnd-xStart)/xResolution));
                int y = (int)(yStart+(j*(yEnd-yStart)/yResolution));

                double noise = (1+simplexNoise.getNoise(x, y))/2;

                Material material;
                if(noise < 0.495F)
                    material = Material.WATER_DEEP;
                else if(noise < 0.5F)
                    material = Material.WATER;
                else if(noise < 0.525F)
                    material = Material.GRASS;
                else
                    material = Material.SAND;
                tiles[i][j] = new Tile(material);

                data[i][j] = noise;
            }
        }
    }

    public boolean equals(Object object){
        if(!(object instanceof Chunk))
            return false;

        Chunk chunk = (Chunk) object;
        return CHUNK_X == chunk.CHUNK_X && CHUNK_Y == chunk.CHUNK_Y;
    }

    public void tick() {
        for(int x = 0; x < tiles.length; x++)
            for(int y = 0; y < tiles[0].length; y++)
                tiles[x][y].tick();
    }
}
