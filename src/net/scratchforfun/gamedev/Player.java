package net.scratchforfun.gamedev;

import java.awt.*;

/**
 * Created by Magnus on 05/10/2014.
 */
public class Player {

    public Image texture;

    public int posX;
    public int posY;

    public Player(){
        texture = TextureManager.loadTexture("player");
    }

}
