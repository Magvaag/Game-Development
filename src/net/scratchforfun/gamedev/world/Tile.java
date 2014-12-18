package net.scratchforfun.gamedev.world;

import net.scratchforfun.gamedev.util.TextureManager;

import java.awt.*;
import static net.scratchforfun.gamedev.reference.References.*;
import static net.scratchforfun.gamedev.Game.*;

/**
 * Created by Magnus on 05/10/2014.
 */
public class Tile {

    public Material material;
    public Image texture;

    public Tile(Material material){
        this.material = material;
        this.texture = TextureManager.loadTexture(this.material.getRandomResourceId());
    }

    public void render(Graphics g){
        this.render(g, 0, 0);
    }

    public void render(Graphics g, int x, int y){
        this.render(g, x, y, ceil(TILE_SIZE * PIXEL_SIZE * PIXEL_SCALE_WIDTH), ceil(TILE_SIZE * PIXEL_SIZE * PIXEL_SCALE_HEIGHT));
    }

    public void render(Graphics g, int x, int y, int width, int height){
        g.drawImage(texture, x, y, width, height, null);
    }

    public void tick(){

    }

}
