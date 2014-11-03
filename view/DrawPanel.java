package draw.view;

import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * <p>
 * A plain JPanel that paints squares based on points in the model.
 * <p>
 * This view has a mouse listener control that calls add points.
 * It should just listen to changes on the model but it's nice to have
 * the repaint happen so directly.
 * 
 * 
 * @see draw.control.Mouser#setPanel(DrawPanel panel)
 * @TODO remove model
 * @TODO insert source control version and edit date #?  ${version}
 * 
 */
@SuppressWarnings("serial")
public class DrawPanel extends JPanel {

	/**
	 * This is the model, for now.
	 * 
	 * @TODO: Bit-field for memory savings?
	 * @TODO: BufferedImage? For grid stencil anyway
	 */
	java.util.List<Point> points = new ArrayList<Point>();

	
	/** width of grid */
	private int scaleX = 40;
	/** height of grid */
	private int scaleY = 40;

	
	/**
	 * Constructor sets double buffering
	 */
	public DrawPanel() {
		super();
	}

	/**
	 * Adds a Point in pixel coordinates to the model.
	 * <p>
	 * Calls repaint with that as top-left and current scale.
	 * 
	 * @param pt top-left
	 * @see {@link #addPoints(Set points)}
	 */
	public void addPoint(Point pt) {
		points.add(pt);
		repaint(pt.x, pt.y, scaleX, scaleY);
	}

	/**
	 * Adds a Set of Points in pixel coordinates to the model.
	 * <p>
	 * Individual calls to repaint are combined by Swing.
	 * <p>
	 * 
	 * @param pts Set of points to plot
	 * @see {@link #addPoint(Point pt)}
	 */
	public void addPoints(Set<Point> pts) {
		points.addAll(pts);
		for (Point p : pts) {
			repaint(p.x, p.y, scaleX, scaleY);
		}
	}
	
	/**
	 * Paint the squares in the model. 
	 * <p>
	 * Individual calls to repaint are combined by Swing.
	 *
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for (Point p : points) {
			g2d.fillRect(p.x, p.y, scaleX, scaleY);
		}
	}
	
	/**
	 * @return scale in the X axis
	 */
	public int getScaleX() {
		return scaleX;
	}

	/**
	 * @param scalex X axis scale
	 */
	public void setScaleX(int scalex) {
		this.scaleX = scalex;
	}

	/**
	 * @return scale in the Y axis
	 */
	public int getScaleY() {
		return scaleY;
	}

	/**
	 * @param scaley Y axis scale
	 */
	public void setScaleY(int scaley) {
		this.scaleY = scaley;
	}

}