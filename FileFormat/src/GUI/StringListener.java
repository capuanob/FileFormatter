package GUI;

import java.util.List;

/**
 *
 * @author Bailey Capuano
 * This interface facilitates transmission of file contents to MainFrame with low coupling.
 */

public interface StringListener  {
	/*
	 * This function acts as an action listener for MainFrame's ability to access input file content.
	 * @param content: The content of the input file.
	 */
	public void inputFileChosen(String content);
}
