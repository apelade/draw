package draw;

import javax.swing.*;

import draw.control.Mouser;
import draw.view.DrawPanel;
import draw.control.Mouser;
/**
 * @author apelade
 *
 */
public class App {

	JFrame frame;
	DrawPanel panel;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new App().show();
	}
	
	public App() {
		frame = new JFrame();
		frame.setSize(400, 200);
		panel = new DrawPanel();
        Mouser mouser = new Mouser(panel);
		frame.getContentPane().add(panel);	
	}
	
    public DrawPanel getPanel() {
        return panel;
    }

    public void setPanel(DrawPanel panel) {
        this.panel = panel;
    }

    public void show() {
    	frame.setVisible(true);
    }
}

