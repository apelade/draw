package draw;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Arrays;
import java.util.Set;
import org.junit.Test;

public class AlgoTest {
    int scalex = 10;
    int scaley = 10;

    @Test
    public void test() {
        System.out.println("" + testGetSpots());
    }

    public Set<Point> testGetSpots() {

        Object[] inputs = { new Point(25, 455), new Point(60, 290), scalex, scaley };
        System.out.println("Inputs: " + Arrays.toString(inputs));
        Set<Point> pts = Algo.getSpots(
                (Point) inputs[0],
                (Point) inputs[1],
                (Integer) inputs[2],
                (Integer) inputs[3],
                true);
       // System.out.println("Outputs: " + Arrays.toString(pts.toArray()));
        assertTrue(testGetSpotsOutputDivisibleByTen(pts));
        assertEquals(
          Arrays.toString(pts.toArray()),
          "[java.awt.Point[x=20,y=450], java.awt.Point[x=20,y=440], java.awt.Point[x=30,y=430], java.awt.Point[x=30,y=420], java.awt.Point[x=30,y=410], java.awt.Point[x=30,y=400], java.awt.Point[x=40,y=390], java.awt.Point[x=40,y=380], java.awt.Point[x=40,y=370], java.awt.Point[x=40,y=360], java.awt.Point[x=50,y=350], java.awt.Point[x=50,y=340], java.awt.Point[x=50,y=330], java.awt.Point[x=50,y=320], java.awt.Point[x=60,y=310], java.awt.Point[x=60,y=300], java.awt.Point[x=60,y=290]]"
        );
        return pts;
    }

    public boolean testGetSpotsOutputDivisibleByTen(Set<Point> points) {
        for (Point p : points) {
            if (p.x % scalex != 0 || p.y % scaley != 0) {
                return false;
            }
        }
        return true;
    }

}
