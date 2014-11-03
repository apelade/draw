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
	 * 
	 * @param toSnap
	 *            Point which will be altered
	 * @param scalex
	 *            Horizontal scale of grid to snap onto.
	 * @param scaley
	 *            Vertical scale of grid to snap onto.
	 * @return toSnap Point with location changed.
	 */
	public static Point snapPointInPlace(Point toSnap, int scalex, int scaley) {
		toSnap.setLocation((toSnap.x / scalex) * scalex, (toSnap.y / scaley)
				* scaley);
		return toSnap;
	}

	/**
	 * Generate the points which define the top-left of squares symmetrically
	 * connecting a start and stop point.
	 * <p>
	 * 
	 * <pre>
	 * Asymmetry is not more than one square:
	 * 
	 * ###       =>       ####        =>       ####          NOT!:   ######
	 *    ###                 ###                  ####                    ##
	 * 
	 *               
	 * Distribution of longer and shorter blocks can be symmetrical:     
	 *        #
	 *      ##
	 *    ## 
	 *  ## 
	 * #
	 * 
	 * However calculations are from one end and don't ensure symmetry.
	 * Currently (10, 10) to (90, 30) does not give 3 X 3 but instead:
	 * ##  
	 *   ####
	 *       ###
	 * </pre>
	 * 
	 * 
	 * 
	 * @param start
	 *            Point in pixel coordinates
	 * @param stop
	 *            Point in pixel coordinates
	 * @param width
	 *            grid horizontal scale in pixels
	 * @param height
	 *            grid vertical scale in pixels
	 * @param snap
	 *            snap point to nearest grid defined by width and height
	 * @return connecting points between start and stop
	 * 
	 */
	public static Set<Point> getSpots(Point start, Point stop, int width,
			int height, boolean snap) {
		if (snap) {
			start = Algo.snapPointInPlace(start, width, height);
			stop = Algo.snapPointInPlace(stop, width, height);
		}
		double xch = stop.x - start.x;
		double ych = stop.y - start.y;
		double numSpots = Math.max(Math.abs(xch / width),
				Math.abs(ych / height));
		if(numSpots % 2 == 0) {
			numSpots +=1;
		}
		Set<Point> spots = new LinkedHashSet<Point>(512);
		double lenx = (xch / numSpots);
		double leny = (ych / numSpots);
		double x = start.x + .5 * width;
		double y = start.y + .5 * height;

		for (int i = 0; i < numSpots + 1; i++) {
		//	Point toAdd = new Point((int) Math.round(x), (int) Math.round(y));
			Point toAdd = new Point((int) x, (int) y);

			if (snap == true) {
				spots.add(snapPointInPlace(toAdd, width, height));
			} else {
				spots.add(toAdd);
			}
			x = x + lenx;
			y = y + leny;
		}
		return spots;
	}
}
