package net.scratchforfun.gamedev;

import javax.swing.*;

/**
 * Created by Scratch on 9/23/2014.
 */
public class Main {

    public static void main(String[] args){
        // SwingUtilities.invokeLater();
        new Main();
    }

    public Main(){
        JFrame frame = new JFrame("Game Development - by ScratchForFun");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Custom Icon
        // Custom Cursor

        // Screen

        frame.setVisible(true);
    }

}
