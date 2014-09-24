package net.scratchforfun.gamedev;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Scratch on 9/23/2014.
 */
public class Main {

    public static final String TITLE = "Game Development - by ScratchForFun";
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }

    public Main(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        SCREEN_WIDTH = toolkit.getScreenSize().width;
        SCREEN_HEIGHT = toolkit.getScreenSize().height;

        JFrame frame = new JFrame(TITLE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Custom Icon
        // Custom Cursor

        frame.add(new Screen(frame));

        frame.setVisible(true);
    }

}
