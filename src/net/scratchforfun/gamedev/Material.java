package net.scratchforfun.gamedev;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Magnus on 08/10/2014.
 */
public class Material {

    public static final Material GRASS = new Material("grass");
    public static final Material SAND = new Material("sand");
    public static final Material WATER = new Material("water");
    public static final Material WATER_DEEP = new Material("water_deep");

    private List<String> resourceIds;

    public Material(String resourceId){
        resourceIds = new ArrayList<String>();
        resourceIds.add(resourceId);
    }

  /**
   * public Material(String[] resourceIds){
   *       resourceIds.
   */   }

    public String getResourceId(int i){
        return resourceIds.get(i);
    }

    Random random = new Random();
    public String getRandomResourceId(){
        return resourceIds.get(random.nextInt(resourceIds.size()));
    }

}
