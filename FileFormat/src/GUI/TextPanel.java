package GUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * @author Bailey Capuano
 * Used for displaying pre-formatted and post-formatted file contents. 
 */
public class TextPanel extends JPanel {

	private JTextArea textArea; // JComponent used to display file contents (multi-line text-field)
	
	/**
	 * Default constructor
	 */
	public TextPanel() {
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(textArea), BorderLayout.CENTER); // Designed to take up entire panel. 
		
	}
	
	/**
	 * Adds inputed text to textArea.
	 * @param text: String to be added to the textArea
	 */
	public void appendText(String text) {
		if (text.endsWith("\n")) {
			this.textArea.append(text);
		} else {
			this.textArea.append(text + "\n");
		}
	}
}
