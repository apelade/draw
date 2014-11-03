package draw.control;
import draw.Algo;
import draw.view.DrawPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;

/**
 * Listener for mouse events on {@link draw.view.DrawPanel}
 * <p>
 * Fills in between mouse events when drawing lines.
 */
public class Mouser extends MouseAdapter {

    /**
     * Point tracking previous mouse drag detection
     */
    Point prevPt;
    
    /**
     * Point tracking mouse drag current location
     */
    Point cursorPt;
    
    /**
     * Target view to listen for events
     */
    DrawPanel panel;
    
    /**
     * Set the target view.
     * @param panel DrawPanel to listen on
     */
    public Mouser(DrawPanel panel) {
        setPanel(panel);
    }
    
    /**
     * Set view (for model access) and listen to view's mouse events. 
     * @param panel DrawPanel view
     */
    public void setPanel(DrawPanel panel){
        this.panel = panel;
        panel.addMouseMotionListener(this);
        panel.addMouseListener(this);
    }

    /**
     * Begin tracking the mouse point, and add snapped current point to the model.
     * <p>
     * @param e MouseEvent
     * @see draw.Algo#snapPointInPlace(Point, int, int)
     */    
    @Override
    public void mousePressed(MouseEvent e) {
        prevPt = e.getPoint();
        cursorPt = prevPt;
        Point newPt = Algo.snapPointInPlace(new Point(cursorPt.x, cursorPt.y),panel.getScaleX(), panel.getScaleY());
        panel.addPoint(newPt);
    }

    /**
     * Update the latest mouse point, fetch snapped points between it and the previous point.
     * Then add them to the model.
     * @param e MouseEvent
     * @see draw.Algo#snapPointInPlace(Point, int, int)
     * @see draw.Algo#getSpots(Point, Point, int, int, boolean)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        prevPt = cursorPt;
        cursorPt = e.getPoint();
        panel.addPoints((Algo.getSpots(prevPt, cursorPt, panel.getScaleX(), panel.getScaleY(), true)));
    }

    /**
     * End tracking the mouse.
     * @param e MouseEvent
     * 
     * @TODO: final call to addPoints?
     * @TODO: create UndoableEdit at this point, add to UndoManager 
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        prevPt = null;
        cursorPt = null;
        // spots.clear();
    }

}
