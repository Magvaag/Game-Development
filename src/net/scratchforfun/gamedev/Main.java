package net.scratchforfun.gamedev;

import net.scratchforfun.gamedev.reference.References;

import static net.scratchforfun.gamedev.reference.References.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Scratch on 9/23/2014.
 */
public class Main {

    public static void main(String[] args){
        // Makes sure the GUI updates correctly
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }

    public Main(){
        // You can customize the screen size under References
        Dimension maxScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if(SCREEN_WIDTH == 0)
            SCREEN_WIDTH = maxScreenSize.width;
        if(SCREEN_HEIGHT == 0)
            SCREEN_HEIGHT = maxScreenSize.height;

        // Game object
        GAME = new Game();
        GAME.screen = GAME.new Screen();
        GAME.start();

        // Creates a Window
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Determine whether or not go undecorated
        boolean fullscreen = References.FULLSCREEN_WINDOWED;
        if(SCREEN_WIDTH == maxScreenSize.width && SCREEN_HEIGHT == maxScreenSize.height)
            fullscreen = true;

        if(fullscreen){
            frame.setUndecorated(true);
            frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        }else{
            frame.getContentPane().setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
            frame.pack();
        }

        // Frame listeners
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(!PRESSED_KEYS.contains(e.getKeyCode())) PRESSED_KEYS.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                PRESSED_KEYS.remove((Integer)e.getKeyCode());
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
                MOUSE_X = e.getX();
                MOUSE_Y = e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                MOUSE_X = e.getX();
                MOUSE_Y = e.getY();
            }
        });

        // Custom Icon
        // Custom Cursor

        // Adds the screen
        frame.add(GAME.screen);

        // Makes the window visible
        frame.setLocationRelativeTo(null); // If you use custom screen size, this will make sure its centered
        frame.setVisible(true);
    }

}
