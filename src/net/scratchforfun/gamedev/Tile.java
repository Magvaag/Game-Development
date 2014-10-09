package net.scratchforfun.gamedev;

import java.awt.*;

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

}
