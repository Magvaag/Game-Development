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

}
