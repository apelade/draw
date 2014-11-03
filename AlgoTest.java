package draw;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Arrays;
import java.util.Set;
import org.junit.Test;

/**
 * @author apelade
 * test line drawing for evenness
 */
public class AlgoTest {
	
	/**
	 * Scale of horizontal and vertical snap grid
	 */
    static final int scalex = 10;
    static final int scaley = 10;

    @Test
    public void test() {
       testGetSpots();
    }

    // There must be something to do with junit here
    public static Set<Point> getPointsForArray(Point start, Point stop){
        Set<Point> pts = Algo.getSpots(
        		start,
        		stop,
        		scalex,
        		scaley,
                true);
//        System.out.println("Outputs: " + Arrays.toString(pts.toArray()));
        assertTrue(testGetSpotsOutputDivisibleByScale(pts));
        return pts;
    }
    /**
     * Show that the layout of generated spots is correct
     */
    public void testGetSpots() {

        /**
         *          #
         *        ##
         *      ##
         *    ##
         *  ##
         * #
         * 
         */
        Set<Point> pts = getPointsForArray(new Point(10, 100), new Point(100, 50));
        assertEquals(
        	Arrays.toString(pts.toArray()),
            "[java.awt.Point[x=10,y=100], java.awt.Point[x=20,y=90], java.awt.Point[x=30,y=90], java.awt.Point[x=40,y=80], java.awt.Point[x=50,y=80], java.awt.Point[x=60,y=70], java.awt.Point[x=70,y=70], java.awt.Point[x=80,y=60], java.awt.Point[x=90,y=60], java.awt.Point[x=100,y=50]]"
        );
        
        /**
         * 
         * ### 
         *    ####
         * 
         */
        pts = getPointsForArray(new Point(10, 10), new Point(70, 20));
        assertEquals(
        	Arrays.toString(pts.toArray()),
            "[java.awt.Point[x=10,y=10], java.awt.Point[x=20,y=10], java.awt.Point[x=30,y=10], java.awt.Point[x=40,y=20], java.awt.Point[x=50,y=20], java.awt.Point[x=60,y=20], java.awt.Point[x=70,y=20]]"
        );
        
        /**
         * 
         * ####  
         *     ####
         * 
         */
        pts = getPointsForArray(new Point(10, 10), new Point(80, 20));
        assertEquals(
        	Arrays.toString(pts.toArray()),
            "[java.awt.Point[x=10,y=10], java.awt.Point[x=20,y=10], java.awt.Point[x=30,y=10], java.awt.Point[x=40,y=10], java.awt.Point[x=50,y=20], java.awt.Point[x=60,y=20], java.awt.Point[x=70,y=20], java.awt.Point[x=80,y=20]]"
        );
        
        /**
         * 
         * ####  
         *     #####
         * 
         */
        pts = getPointsForArray(new Point(10, 10), new Point(90, 20));
        assertEquals(
        	Arrays.toString(pts.toArray()),
            "[java.awt.Point[x=10,y=10], java.awt.Point[x=20,y=10], java.awt.Point[x=30,y=10], java.awt.Point[x=40,y=10], java.awt.Point[x=50,y=20], java.awt.Point[x=60,y=20], java.awt.Point[x=70,y=20], java.awt.Point[x=80,y=20], java.awt.Point[x=90,y=20]]"
        );
        
        /**
         * @TODO: this should be 3 x 3 ideally
         * 
         * ##  
         *   ####
         *       ###
         * 
         */
        pts = getPointsForArray(new Point(10, 10), new Point(90, 30));
        assertEquals(
        	Arrays.toString(pts.toArray()),
            "[java.awt.Point[x=10,y=10], java.awt.Point[x=20,y=10], java.awt.Point[x=30,y=20], java.awt.Point[x=40,y=20], java.awt.Point[x=50,y=20], java.awt.Point[x=60,y=20], java.awt.Point[x=70,y=30], java.awt.Point[x=80,y=30], java.awt.Point[x=90,y=30]]"
        );
        
        /**
         * 
         * ###  
         *    ####
         *        ###
         * 
         */
        pts = getPointsForArray(new Point(0, 10), new Point(90, 30));
        assertEquals(
        	Arrays.toString(pts.toArray()),
            "[java.awt.Point[x=0,y=10], java.awt.Point[x=10,y=10], java.awt.Point[x=20,y=10], java.awt.Point[x=30,y=20], java.awt.Point[x=40,y=20], java.awt.Point[x=50,y=20], java.awt.Point[x=60,y=20], java.awt.Point[x=70,y=30], java.awt.Point[x=80,y=30], java.awt.Point[x=90,y=30]]"
        );
        
    	/**
    	*                   ##
    	*               ####
    	*           ####
    	*       ####  
    	*   ####   
    	* ##
    	*/                
        pts = getPointsForArray(new Point(20, 60), new Point(220, 10));
        assertEquals(
        	Arrays.toString(pts.toArray()),
            "[java.awt.Point[x=20,y=60], java.awt.Point[x=30,y=60], java.awt.Point[x=40,y=60], java.awt.Point[x=50,y=50], java.awt.Point[x=60,y=50], java.awt.Point[x=70,y=50], java.awt.Point[x=80,y=50], java.awt.Point[x=90,y=40], java.awt.Point[x=100,y=40], java.awt.Point[x=110,y=40], java.awt.Point[x=120,y=40], java.awt.Point[x=130,y=30], java.awt.Point[x=140,y=30], java.awt.Point[x=150,y=30], java.awt.Point[x=160,y=30], java.awt.Point[x=170,y=20], java.awt.Point[x=180,y=20], java.awt.Point[x=190,y=20], java.awt.Point[x=200,y=20], java.awt.Point[x=210,y=10], java.awt.Point[x=220,y=10]]"
        );
    }
    

    public static boolean testGetSpotsOutputDivisibleByScale(Set<Point> points) {
        for (Point p : points) {
            if (p.x % scalex != 0 || p.y % scaley != 0) {
                return false;
            }
        }
        return true;
    }

}
