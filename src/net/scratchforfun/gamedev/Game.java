package net.scratchforfun.gamedev;

import net.scratchforfun.gamedev.reference.References;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

import static net.scratchforfun.gamedev.reference.References.*;
import static net.scratchforfun.gamedev.reference.ReferencesImage.GRASS;

/**
 * Created by Scratch on 9/24/2014.
 */
public class Game {

    public Screen screen;

    public Map map;
    public GameThread thread;

    public float PIXEL_SCALE_WIDTH;
    public float PIXEL_SCALE_HEIGHT;

    public int FPS;
    public int UPS;

    public Game(){
        map = new Map(new Player());

        PIXEL_SCALE_WIDTH = References.SCREEN_WIDTH / References.OPTIMISED_SCREEN_WIDTH;
        PIXEL_SCALE_HEIGHT = References.SCREEN_HEIGHT / References.OPTIMISED_SCREEN_HEIGHT;

        // Starts the game thread
        thread = new GameThread();
    }

    public void update(){
        map.checkChunks();

        // Update Input
        updateInput();
    }

    private void updateInput(){
        // Keys
        for(int key : PRESSED_KEYS){
            if(key == KeyEvent.VK_W)
                map.player.posY--;

            if(key == KeyEvent.VK_A)
                map.player.posX--;

            if(key == KeyEvent.VK_S)
                map.player.posY++;

            if(key == KeyEvent.VK_D)
                map.player.posX++;
        }
    }

    // Rounds the number up
    public static int ceil(double x){
        return (int)Math.ceil(x);
    }

    // Rounds the number down
    public static int floor(double x){
        int xi = (int)x;
        return x<xi ? xi-1 : xi;
    }

    /**
     * Created by Scratch on 9/24/2014.
     */
    public class Screen extends JPanel {

        public void paintComponent(Graphics g){
            // Clears the screen
            g.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

            // Renders background of screen
            renderBackground(g);

            // Renders main part of screen
            render(g);

            // Renders foreground of screen
            renderForeground(g);
        }

        private void renderBackground(Graphics g){
            for(Chunk chunk : map.loadedChunks){
                int posX = ceil((chunk.CHUNK_X*TILE_AMOUNT_X*TILE_SIZE*PIXEL_SIZE - map.player.posX*TILE_SIZE*PIXEL_SIZE - TILE_SIZE*PIXEL_SIZE/2)*PIXEL_SCALE_WIDTH + SCREEN_WIDTH/2);
                int posY = ceil((chunk.CHUNK_Y*TILE_AMOUNT_Y*TILE_SIZE*PIXEL_SIZE - map.player.posY*TILE_SIZE*PIXEL_SIZE - TILE_SIZE*PIXEL_SIZE/2)*PIXEL_SCALE_HEIGHT + SCREEN_HEIGHT/2);

                for(int x = 0; x < chunk.tiles.length; x++){
                    for(int y = 0; y < chunk.tiles[0].length; y++){
                        g.drawImage(chunk.tiles[x][y].texture, ceil(x*TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_WIDTH) + posX, ceil(y*TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_HEIGHT) + posY, ceil(TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_WIDTH), ceil(TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_HEIGHT), null);
                    }
                }
                g.drawLine(posX, 0, posX, SCREEN_HEIGHT);
                g.drawLine(0, posY, SCREEN_WIDTH, posY);
                g.drawString("Chunk("+chunk.CHUNK_X+", "+chunk.CHUNK_Y+")", ceil(posX), ceil(posY+10));
            }

            /*for(int x = 0; x < 20; x++){
                for(int y = 0; y < 20; y++){
                    g.drawImage(GRASS, x*TILE_SIZE*PIXEL_SIZE, y*TILE_SIZE*PIXEL_SIZE, TILE_SIZE*PIXEL_SIZE, TILE_SIZE*PIXEL_SIZE, null);
                }
            }*/
        }

        private void render(Graphics g){
            // Render player
            g.drawImage(map.player.texture, References.SCREEN_WIDTH/2 - ceil(TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_WIDTH/2), References.SCREEN_HEIGHT/2 - ceil(TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_HEIGHT/2), ceil(TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_WIDTH), ceil(TILE_SIZE*PIXEL_SIZE*PIXEL_SCALE_HEIGHT), null);
        }

        private void renderForeground(Graphics g){
            resetString();
            drawString(g, "FPS: " + FPS);
            drawString(g, "UPS: " + UPS);
            drawString(g, "");
            drawString(g, "AmountKeysPressed: " + PRESSED_KEYS.size());
            drawList(g, toStringList("KeyPressed: ", PRESSED_KEYS));
            drawString(g, "");
            drawString(g, "MouseX: " + MOUSE_X);
            drawString(g, "MouseY: " + MOUSE_Y);
            drawString(g, "");
            drawString(g, "PlayerX: " + map.player.posX);
            drawString(g, "PlayerY: " + map.player.posY);
        }

        int spaceY = 15;
        private void resetString(){
            spaceY = 15;
        }
        private void drawString(Graphics g, String text){
            g.setColor(Color.BLACK);
            g.drawString(text, 5, spaceY);
            spaceY+=15;
        }
        private void drawList(Graphics g, java.util.List<String> list){
            for(String text : list){
                drawString(g, text);
            }
        }
        private java.util.List<String> toStringList(String pre, java.util.List list){
            java.util.List<String> stringList = new ArrayList<String>();

            for(Object object : list){
                stringList.add(pre+object.toString());
            }

            return stringList;
        }
    }

    /**
     * Starts the game
     */
    public void start(){
        thread.start();
    }

}
