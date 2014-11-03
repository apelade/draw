package draw;

import javax.swing.*;

import draw.control.Mouser;
import draw.view.DrawPanel;

/**
 * Main class for draw desktop application
 * @author Paul McCulloch
 * @version 0.1
 */
public class App {
	
	/** GUI window */
	JFrame frame;

	/** Drawing area */
	DrawPanel panel;
	
	/**  Mouse controls */
	Mouser mouser;
	
	/**
	 * Entry point
	 * @param args is not used
	 */
	public static void main(String[] args) {
		new App().show();
	}
	
	/**
	 * Initialize the application.
	 */
	public App() {
		frame = new JFrame();
		frame.setSize(400, 200);
		panel = new DrawPanel();
		setPanel(panel);
	}
	
	/**
	 * @return DrawPanel panel
	 */
    public DrawPanel getPanel() {
        return panel;
    }

    /**
     * Set a drawing area for the app.
     * <p>
     * Add mouse controls, and add it to the window.
     * @param panel
     */
    public void setPanel(DrawPanel panel) {
        this.panel = panel;
        mouser = new Mouser(panel);
        frame.getContentPane().add(panel);
    }
    
    /**
     * Show the window.
     */
    public void show() {
    	frame.setVisible(true);
    }
}

