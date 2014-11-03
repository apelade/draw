package draw;

import java.awt.Point;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Static methods for line drawing etc.
 */
public class Algo {

    /**
     * In-place, snap a point to a grid.
     * <p>
     * @param toSnap Point which will be altered
     * @param scalex Horizontal scale of grid to snap onto.
     * @param scaley Vertical scale of grid to snap onto.
     * @return toSnap Point with location changed.
     */
    public static Point snapPointInPlace(Point toSnap, int scalex, int scaley) {
        toSnap.setLocation((toSnap.x / scalex) * scalex, (toSnap.y / scaley) * scaley);
        return toSnap;
    }

    /**
     * Generate the points which define the top-left of squares
     * symmetrically connecting a start and stop point.
     * <p>
     * Symmetry is complete when there is an even number of squares.
     * Asymmetry should not be more than one square:
     * <pre>
     * ###       =>       ####        =>       ####
     *    ###                 ###                  ####
     * 
     * 
     * NOT:   ######
     *              ##
     * </pre>             
     * Distribution of longer and shorter blocks is symmetrical:
     * <pre>
     * 
     * #
     * #
     *  #         
     *  #       0   line  (10, 100) to (100, 50)
     *  #     00
     *  #   00  
     *   #00 
     *  0X   X - intersection of # and 0
     * 0 # 
     *   #
     *    # 
     *    # 
     *    # 
     *    # 
     *     # 
     *     #
     *     #   line (10,20) to (60,220):
     * </pre>               
     *               
     * @param start  Point in pixel coordinates
     * @param stop   Point in pixel coordinates
     * @param width  grid horizontal scale in pixels
     * @param height grid vertical scale in pixels
     * @param snap   snap point to nearest grid defined by width and height
     * @return       connecting points between start and stop
     *
     * @TODO currently (10, 10) to (90, 30) does not give 3 X 3
     */
    public static Set<Point> getSpots(Point start, Point stop, int width, int height, boolean snap) {
        if(snap) {
            start = Algo.snapPointInPlace(start, width, height);
            stop = Algo.snapPointInPlace(stop,  width, height);
        }
        double xch = stop.x - start.x;
        double ych = stop.y - start.y;
        double numSpots = Math.max(Math.abs(xch / width), Math.abs(ych / height));
        Set<Point> spots = new LinkedHashSet<Point>(512);

        if (numSpots == 0) {
            spots.add(start);
        } else {
            double lenx = (xch / numSpots);
            double leny = (ych / numSpots);
            double x = start.x + .5 * width;
            double y = start.y + .5 * height;
            Point toAdd;
            
            for (int i = 0; i < numSpots+1; i++) {
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
