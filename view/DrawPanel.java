package draw.view;
import draw.Algo;

import java.awt.*;
import javax.swing.*;

//import java.awt.geom.*;
import java.awt.Point;
import java.util.*;

public class DrawPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
     Point cursorPt = new Point(20, 240);
     Point prevPt = new Point(302, 400);
    // Point cursorPt = new Point(250, 250);
    // Point prevPt = new Point(50, 50);
   // Point cursorPt = new Point(25, 455);
   // Point prevPt = new Point(60, 290);

    java.util.List<Point> points = new ArrayList<Point>();


    private int scalex = 40;
    private int scaley = 40;

    public DrawPanel() {
        super();
       // addPoints((Algo.getSpots(prevPt, cursorPt, scalex, scaley, true)));
    }

    public void addPoint(Point pt) {
         points.add(pt);
         repaint(pt.x, pt.y, scalex, scaley);
    }

    public void addPoints(Set<Point> pts) {
        points.addAll(pts);
        for (Point p : pts) {
            repaint(p.x, p.y, scalex, scaley);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Point p : points) {
            g2d.fillRect(p.x, p.y, scalex, scaley);
        }

    }
    
    public int getScaleX() {
        return scalex;
    }

    public void setScaleX(int scalex) {
        this.scalex = scalex;
    }

    public int getScaleY() {
        return scaley;
    }

    public void setScaleY(int scaley) {
        this.scaley = scaley;
    }

}