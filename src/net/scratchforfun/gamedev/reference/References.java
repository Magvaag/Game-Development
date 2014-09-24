package net.scratchforfun.gamedev.reference;

import net.scratchforfun.gamedev.Game;
import net.scratchforfun.gamedev.GameThread;
import net.scratchforfun.gamedev.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scratch on 9/24/2014.
 */
public class References {

    // MAIN
        public static final String TITLE = "Game Development - by ScratchForFun";
        public static int SCREEN_WIDTH;
        public static int SCREEN_HEIGHT;

    // SCREEN
        public static int PIXEL_SIZE = 3; // TODO: Change this to scale!
        public static int TILE_SIZE = 16;

    // GAME THREAD
        public static int UPDATES_PER_SECOND = 50;
        public static int FRAMES_PER_SECOND = 100;
        public static long MAX_FRAMESKIP = 5;

    // GAME
        public static Game GAME;

    // LISTENER
        public static List<Integer> PRESSED_KEYS = new ArrayList<Integer>();
        public static int MOUSE_X;
        public static int MOUSE_Y;

}
