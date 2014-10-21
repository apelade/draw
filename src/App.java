import java.awt.event.MouseAdapter;

import javax.swing.*;
import java.awt.event.*;

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
		frame.getContentPane().add(panel);	
	}
	
    public void show() {
    	frame.setVisible(true);
    }
}

