package net.scratchforfun.gamedev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scratch on 9/24/2014.
 */
public class Screen extends JPanel implements Runnable {

    List<Integer> keysPressed;

    Thread thread;
    Map map;

    // TODO: Put into reference class
    int pixelSize = 3;

    int mouseX;
    int mouseY;

    int fps = 0;
    int ups = 0;

    public Screen(JFrame frame){
        keysPressed = new ArrayList<Integer>();

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!keysPressed.contains(e.getKeyCode())) keysPressed.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keysPressed.remove((Integer)e.getKeyCode());
            }
        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        map = new Map();

        // Should be last thing to be called
        thread = new Thread(this);
        thread.start();
    }

    private void update(){

    }

    public void paintComponent(Graphics g){
        g.clearRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        int image_width = 16;
        int image_height = 16;

        for(int x = 0; x < 20; x++){
            for(int y = 0; y < 20; y++){
                g.drawImage(ImageLibrary.GRASS, x*image_width*pixelSize, y*image_height*pixelSize, image_width*pixelSize, image_height*pixelSize, null);
            }
        }

        resetString();
        drawString(g, "AmountKeysPressed: " + keysPressed.size());
        for(Integer integer : keysPressed){
            drawString(g, "KeyPressed: " + integer);
        }
        drawString(g, "");
        drawString(g, "MouseX: " + mouseX);
        drawString(g, "MouseY: " + mouseY);
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

    @Override
    public void run() {
        boolean print_fps_to_console = false;

        int UPDATES_PER_SECOND = 50;
        int FRAMES_PER_SECOND = 100;

        long GAME_SKIP_TICKS = 1000 / UPDATES_PER_SECOND;
        long FRAME_SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
        long MAX_FRAMESKIP = 5;

        long next_game_tick = System.currentTimeMillis();
        long next_frame_tick = System.currentTimeMillis();
        long time = System.currentTimeMillis();

        int loops;

        // Variables to update the UPS & FPS variables
        int frames = 0;
        int updates = 0;

        while(true){
            loops = 0;
            while(System.currentTimeMillis() > next_game_tick && loops < MAX_FRAMESKIP){
                update();

                next_game_tick += GAME_SKIP_TICKS;
                updates++;
                loops++;
            }

            if(System.currentTimeMillis() > next_frame_tick){
                next_frame_tick += FRAME_SKIP_TICKS;
                repaint();
                frames++;
            }

            if(time+1000 <= System.currentTimeMillis()){
                time+=1000;

                fps = frames;
                ups = updates;
                updates = 0;
                frames = 0;

                if(print_fps_to_console) {
                    System.out.println("========");
                    System.out.println("FPS: " + fps);
                    System.out.println("UPS: " + ups);
                }
            }
        }
    }

}
