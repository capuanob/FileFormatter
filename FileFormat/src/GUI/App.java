package GUI;

import java.awt.EventQueue;

public class App {

	/**
	 * 
	 * @author Bailey Capuano
	 * Main driver of application, solely calls MainFrame which handles brunt of app functionality. 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { // Closure for multi-threading capability
			public void run() {
				try {
					new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}