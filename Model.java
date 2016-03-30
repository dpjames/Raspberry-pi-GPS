import java.util.Random;

public class Model {
    private int x, y;
    public double scale;

    public Model() {
        x = 0;
        y = 0;
        scale = 1;
    }

    public void zoomIn() {
        scale += .25;
        if (scale > ImageHolder.MaxScale()) {
            scale = ImageHolder.MaxScale();
        }
        recenter();
    }

    public void zoomOut() {
        scale -= .25;
        if (scale <= ImageHolder.minimumScale()) {
            scale = ImageHolder.minimumScale();
        }
        recenter();
    }

    private void recenter() {
        moveUp();
        moveDown();
        moveLeft();
        moveRight();
    }

    private void moveDown() {
        if (y + SPEED < ImageHolder.mapHeight() - Constants.HEIGHT / scale) {
            y += SPEED;
        } else {
            y = (int) (ImageHolder.mapHeight() - Constants.HEIGHT / scale);
        }

    }

    private void moveUp() {
        if (y - SPEED > 0) {
            y -= SPEED;
        } else {
            y = 0;
        }
    }

    private void moveRight() {
        if (x + SPEED < ImageHolder.mapWidth() - Constants.WIDTH / scale) {
            x += SPEED;
        } else {
            x = (int) (ImageHolder.mapWidth() - Constants.WIDTH / scale);
        }
    }

    private void moveLeft() {
        if (x - SPEED > 0) {
            x -= SPEED;
        } else {
            x = 0;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getScale() {
        return scale;
    }

    private double[] loc = new double[2];

    public void setLocation(double lat, double lng) {
        loc[0] = lat;
        loc[1] = lng;
    }

    public double[] getLocation() {
        // REMOVE THIS LATER
        Random rand = new Random();
        // loc[0]=Constants.OZETTE_LAT_RANGE[0] -
        // rand.nextInt((int)(Math.abs(Constants.OZETTE_LAT_RANGE[0] -
        // Constants.OZETTE_LAT_RANGE[1]) * 10000 ))/10000.0;
        // loc[1]=Constants.OZETTE_LONG_RANGE[0] +
        // rand.nextInt((int)(Math.abs(Constants.OZETTE_LONG_RANGE[0] -
        // Constants.OZETTE_LONG_RANGE[1]) * 10000) )/10000.0;;

        // ozette locations
        // loc[0] = 48.1492;
        // loc[1] = -124.6929;
        //
        // olympic location
        // loc[0] = 47.8391;
        // loc[1] = -123.7015;
        //
        loc[0] = 47.8319;
        loc[1] = -123.6918;
        // System.out.println(loc[0] + " " + loc[1]);
        // REMOVE LATER ^
        return loc;
    }

    public void findLocation() {
        // gps stuff will go here
    }

    public void moveScreen(int x2, int y2) {
        x2 = x2 - (Constants.WIDTH / 2);
        y2 = -(y2 - (Constants.HEIGHT / 2));
        if (Math.abs(x2) > Math.abs(y2)) {
            if (x2 > 0) {
                moveRight();
            } else {
                moveLeft();
            }
        } else {
            if (y2 > 0) {
                moveUp();
            } else {
                moveDown();
            }
        }
    }

    private static final int SPEED = 30;

    public void moveToLocation() {
        x = Constants.findLongX(loc[1]);
        y = Constants.findLatY(loc[0]);
        x -= Constants.WIDTH / scale / 2;
        y -= Constants.HEIGHT / scale / 2;
        recenter();

    }

    private boolean settingsOpen = false;

    public void openSettings() {
        settingsOpen = true;
    }

    public void closeSettings() {
        settingsOpen = false;
    }

    public boolean settingsOpen() {
        return settingsOpen;
    }

    public void openNewMap() {
        x = 0;
        y = 0;
        scale = 1;
    }
}
