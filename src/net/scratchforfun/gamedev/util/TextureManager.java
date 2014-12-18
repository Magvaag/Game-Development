package net.scratchforfun.gamedev.util;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Magnus on 08/10/2014.
 */
public class TextureManager {

    public static Image loadTexture(String resourceId){
        return new ImageIcon("res/"+resourceId+".png").getImage();
    }

}
