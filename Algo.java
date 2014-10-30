package draw;

import java.awt.Point;
import java.util.LinkedHashSet;
import java.util.Set;

public class Algo {

    public static Point snapPointInPlace(Point toSnap, int scalex, int scaley) {
        toSnap.setLocation((toSnap.x / scalex) * scalex, (toSnap.y / scaley) * scaley);
        return toSnap;
    }
    
    private static transient Set<Point> spots = new LinkedHashSet<Point>(512);

    public static Set<Point> getSpots(Point start, Point stop, int width, int height, boolean snap) {
        if(snap) {
            start = Algo.snapPointInPlace(start, width, height);
            stop = Algo.snapPointInPlace(stop,  width, height);
        }
        double xch = stop.x - start.x;
        double ych = stop.y - start.y;
        double numSpots = Math.max(Math.abs(xch / width), Math.abs(ych / height));
        spots.clear();

        if (numSpots == 0) {
            spots.add(start);
        } else {
            double lenx = (xch / numSpots);
            double leny = (ych / numSpots);
            double x = start.x + .5 * width;
            double y = start.y + .5 * height;
            Point toAdd;
            
            for (int i = 0; i < numSpots+1; i++) {
                //toAdd = new Point((int)Math.round(x), (int) Math.round(y));
                toAdd = new Point((int)x, (int)y);
                if (snap == true) {
                    spots.add(snapPointInPlace(toAdd, width, height));
                }else {
                    spots.add(toAdd);
                }
                x = x + lenx;
                y = y + leny;
            }
        }
        return spots;
    }
}
