package draw.control;
import draw.Algo;
import draw.view.DrawPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
public class Mouser extends MouseAdapter {


    Point prevPt;
    Point cursorPt;
    DrawPanel panel;
    
    public Mouser(DrawPanel panel) {
        setPanel(panel);
    }
    
    public void setPanel(DrawPanel panel){
        this.panel = panel;
        panel.addMouseMotionListener(this);
        panel.addMouseListener(this);
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        prevPt = cursorPt;
        cursorPt = e.getPoint();
        panel.addPoints((Algo.getSpots(prevPt, cursorPt, panel.getScaleX(), panel.getScaleY(), true)));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        prevPt = e.getPoint();
        cursorPt = prevPt;
        Point newPt = Algo.snapPointInPlace(new Point(cursorPt.x, cursorPt.y),panel.getScaleX(), panel.getScaleY());
        panel.addPoint(newPt);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        prevPt = null;
        cursorPt = null;
        // spots.clear();
    }

}
