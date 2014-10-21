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
    
	public static void snapPointInPlace(Point toSnap, int scale) {
		toSnap.setLocation( (toSnap.x / scale) * scale, (toSnap.y / scale) * scale);
	}
	
	public DrawPanel() {
		super();
		super.setPreferredSize(new java.awt.Dimension(50, 50));
		setBackground(java.awt.Color.RED);
		Mouser mouser = new Mouser();
		this.addMouseMotionListener(mouser);
		this.addMouseListener(mouser);
	}

	class Mouser extends MouseAdapter {
		public void mouseDragged(MouseEvent e) {
			prevPt = cursorPt;
			cursorPt = e.getPoint();
			snapPointInPlace(cursorPt, scale);
			points.add(cursorPt);
			repaint();
		}
		public void mousePressed(MouseEvent e) {
			prevPt = e.getPoint();
			cursorPt = prevPt;
			repaint();
		}
		public void mouseReleased(MouseEvent e) {
			prevPt = null;
			cursorPt = null;
		}
	}


	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		for(Point p: points){
			g2d.fillRect(p.x, p.y, scale, scale);
		}
	}


}