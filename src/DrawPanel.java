import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import java.awt.geom.*;
import java.awt.Point;
import java.util.*;

class DrawPanel extends JPanel {

    Point cursorPt = null;
    Point prevPt = null;
    java.util.List<Point> points = new ArrayList<Point>();
    int scale = 10;
    static Set<Point> spots = new LinkedHashSet<Point>();

    public DrawPanel() {
        super();
//        super.setPreferredSize(new java.awt.Dimension(50, 50));
//        setBackground(java.awt.Color.RED);
        Mouser mouser = new Mouser();
        this.addMouseMotionListener(mouser);
        this.addMouseListener(mouser);
    }

    class Mouser extends MouseAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            prevPt = cursorPt;
            cursorPt = e.getPoint();
            points.addAll((getSpots(prevPt, cursorPt, scale, scale)));
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            prevPt = e.getPoint();
            cursorPt = prevPt;
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            prevPt = null;
            cursorPt = null;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (Point p : points) {
            g2d.fillRect(p.x, p.y, scale, scale);
        }
    }

    public static void snapPointInPlace(Point toSnap, int scale) {
        toSnap.setLocation((toSnap.x / scale) * scale, (toSnap.y / scale)
                * scale);
    }

    // Use to draw straight lines between sampled points
    // should be only about 1-3 blocks depending on hardware so not bad effect
    // on smoothness
    // TODO better algorithm?: https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm
    // or ensure HotSpot can take out some of the math.
    public static Set<Point> getSpots(Point start, Point stop, int width,
            int height) {
        double xch = stop.x - start.x;
        double ych = stop.y - start.y;
        double numSpots = Math.max(Math.abs(xch / width),
                Math.abs(ych / height));
        spots.clear();
        if (numSpots == 0) {
            spots.add(start);
            return spots;
        } else {
            double lenx = (xch / numSpots) * width;
            double leny = (ych / numSpots) * height;
            double centerx = (start.x * width) + (0.5 * width);
            double centery = (start.y * height) + (0.5 * height);
            for (int i = 0; i < numSpots; i++) {
                Point gridPt = new Point(((int) centerx / width),
                        ((int) centery / height));
                snapPointInPlace(gridPt, 10);
                // spots is a set, no repeats
                spots.add(gridPt);
                centerx = centerx + lenx;
                centery = centery + leny;
            }
        }
        return spots;
    }

}