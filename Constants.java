
public class Constants {

    public static final String VERSION = "BETA";
    public static final int WIDTH = 320;
    public static final int HEIGHT = 200;

    // Ozette map
    public static final double[] OZETTE_LAT_RANGE = { 48.1691, 48.1217 };
    public static final double[] OZETTE_LONG_RANGE = { -124.7400, -124.6599 };
    // Olympic map
    public static final double[] OLYMPIC_LAT_RANGE = { 47.8518, 47.8297 };
    public static final double[] OLYMPIC_LONG_RANGE = { -123.7257, -123.6855 };

    private static double[] latMinMax = new double[2];
    private static double latRange;
    private static double latInc;

    public static int findLatY(double lat) {
        double distFromOrigin = Math.abs(lat - latMinMax[0]);
        return (int) (distFromOrigin / latInc);
    }

    private static double[] longMinMax = new double[2];
    private static double longRange;
    private static double longInc;

    public static int findLongX(double lng) {
        double distFromOrigin = Math.abs(lng - longMinMax[0]);
        return (int) (distFromOrigin / longInc);
    }

    public static void changeMap(String s, int height, int width) {
        if (s == "ozette") {
            latMinMax = OZETTE_LAT_RANGE;

            longMinMax = OZETTE_LONG_RANGE;
            latRange = Math.abs(latMinMax[0] - latMinMax[1]);
            latInc = latRange / height;

            longRange = Math.abs(longMinMax[0] - longMinMax[1]);
            longInc = longRange / width;
        } else if (s == "olympic") {
            latMinMax = OLYMPIC_LAT_RANGE;
            longMinMax = OLYMPIC_LONG_RANGE;
            latRange = Math.abs(latMinMax[0] - latMinMax[1]);
            latInc = latRange / height;
            longRange = Math.abs(longMinMax[0] - longMinMax[1]);
            longInc = longRange / width;
        }
    }
}
