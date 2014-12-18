package net.scratchforfun.gamedev.entity;

import com.sun.javafx.geom.Vec2f;
import net.scratchforfun.gamedev.reference.References;
import net.scratchforfun.gamedev.util.TextureManager;

import java.awt.*;

import static net.scratchforfun.gamedev.reference.References.PIXEL_SIZE;
import static net.scratchforfun.gamedev.Game.*;
import static net.scratchforfun.gamedev.reference.References.TILE_SIZE;

/**
 * Created by Magnus on 17/12/2014.
 */
public class Entity {

    public Image texture;
    public Vec2f position;

    public Entity(String textureName){
        texture = TextureManager.loadTexture(textureName);
        position = new Vec2f();
    }

    public void render(Graphics g){
        g.drawImage(texture, References.SCREEN_WIDTH/2 - ceil(TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_WIDTH/2), References.SCREEN_HEIGHT/2 - ceil(TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_HEIGHT/2), ceil(TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_WIDTH), ceil(TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_HEIGHT), null);
    }

    public void tick(){

    }

}
