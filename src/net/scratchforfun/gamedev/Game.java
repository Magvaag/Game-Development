package net.scratchforfun.gamedev;

import javax.swing.*;
import java.awt.*;
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

    public int FPS;
    public int UPS;

    public Game(){
        map = new Map();

        // Starts the game thread
        thread = new GameThread();
    }

    public void update(){

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
            for(int x = 0; x < 20; x++){
                for(int y = 0; y < 20; y++){
                    g.drawImage(GRASS, x*TILE_SIZE*PIXEL_SIZE, y*TILE_SIZE*PIXEL_SIZE, TILE_SIZE*PIXEL_SIZE, TILE_SIZE*PIXEL_SIZE, null);
                }
            }
        }

        private void render(Graphics g){

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
