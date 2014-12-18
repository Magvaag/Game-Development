package net.scratchforfun.gamedev.reference;

import net.scratchforfun.gamedev.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scratch on 9/24/2014.
 */
public class References {

    // MAIN
        public static final String TITLE = "Game Development - by ScratchForFun";
        public static final boolean FULLSCREEN_WINDOWED = false;
        public static final float OPTIMISED_SCREEN_WIDTH = 1920;
        public static final float OPTIMISED_SCREEN_HEIGHT = 1080;
        public static int SCREEN_WIDTH = 800;
        public static int SCREEN_HEIGHT = 600;

    // SCREEN
        public static float PIXEL_SIZE = 1F;
        public static int TILE_SIZE = 16;

    // GAME THREAD
        public static int UPDATES_PER_SECOND = 50;
        public static int FRAMES_PER_SECOND = 100;
        public static long MAX_FRAMESKIP = 5;

    // GAME
        public static Game GAME;
        public static int SEED;

    // LISTENER
        public static List<Integer> PRESSED_KEYS = new ArrayList<Integer>();
        public static int MOUSE_X;
        public static int MOUSE_Y;

    // CHUNK
        public static final int TILE_AMOUNT_X = 16;
        public static final int TILE_AMOUNT_Y = 16;
        public static final int CHUNK_AMOUNT_X = 5;
        public static final int CHUNK_AMOUNT_Y = 5;



}
