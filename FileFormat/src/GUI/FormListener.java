package GUI;

import java.util.EventListener;

/**
 *
 * @author Bailey Capuano
 * This interface facilitates transmission of data in a low coupling manner. 
 */

public interface FormListener extends EventListener {
	/*
	 * This function acts as an event listener for MainFrame's ability to access text-field data.
	 * @param e: The data to be transmitted to MainFrame
	 */
	public void formEventOccurred(FormEvent e);
}
