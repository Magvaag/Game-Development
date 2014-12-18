package net.scratchforfun.gamedev;

import net.scratchforfun.gamedev.entity.Player;
import net.scratchforfun.gamedev.reference.References;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

import static net.scratchforfun.gamedev.reference.References.*;

/**
 * Created by Scratch on 9/24/2014.
 */
public class Game {

    public Screen screen;

    public net.scratchforfun.gamedev.world.Map map;
    public GameThread thread;
    public Player player;

    public static float PIXEL_SCALE_WIDTH;
    public static float PIXEL_SCALE_HEIGHT;

    public int FPS;
    public int UPS;

    public static Game INSTANCE;

    public Game(){
        INSTANCE = this;

        player = new Player();
        map = new net.scratchforfun.gamedev.world.Map();
        map.spawnEntity(player, 0, 0);

        PIXEL_SCALE_WIDTH = References.SCREEN_WIDTH / References.OPTIMISED_SCREEN_WIDTH;
        PIXEL_SCALE_HEIGHT = References.SCREEN_HEIGHT / References.OPTIMISED_SCREEN_HEIGHT;

        // Starts the game thread
        thread = new GameThread();
    }

    public void tick(){
        map.tick();

        // Update Input
        updateInput();
    }

    private void updateInput(){
        // Keys
        for(int key : PRESSED_KEYS){
            if(key == KeyEvent.VK_W)
                player.position.y--;

            if(key == KeyEvent.VK_A)
                player.position.x--;

            if(key == KeyEvent.VK_S)
                player.position.y++;

            if(key == KeyEvent.VK_D)
                player.position.x++;
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

            map.render(g);
            renderForeground(g);
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
            drawString(g, "PlayerX: " + player.position.x);
            drawString(g, "PlayerY: " + player.position.y);
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
