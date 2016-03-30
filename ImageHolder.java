import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageHolder {
    private static BufferedImage map;
    private static String mapName;

    public static void makeImages() {
        map = ozette();
    }

    public static BufferedImage getImg(int x, int y) {
        // TODO Auto-generated method stub
        return null;
    }

    public static BufferedImage getNewMap(String s) {
        if (s == "ozette") {
            return ozette();
        } else if (s == "olympic") {
            return olympic();
        }
        return null;
    }

    public static BufferedImage olympic() {
        try {
            map = ImageIO.read(new File("res/olympic_topo.png"));
            mapName = "olympic";
            Constants.changeMap("olympic", map.getHeight(), map.getWidth());
            return map;
        } catch (Exception e) {

        }
        return null;
    }

    public static String getMapName() {
        return mapName;
    }

    private static BufferedImage ozette() {
        try {
            map = ImageIO.read(new File("res/ozette_topo.PNG"));
            mapName = "ozette";
            Constants.changeMap("ozette", map.getHeight(), map.getWidth());
            return ImageIO.read(new File("res/ozette_topo.PNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int mapWidth() {
        return map.getWidth();
    }

    public static int mapHeight() {
        return map.getHeight();
    }

    public static double MaxScale() {
        if (mapName == "ozette") {
            return 4;
        }
        return 2;
    }

    public static double minimumScale() {
        if (mapName == "ozette") {
            return .5;
        }
        return 0;
    }

}
