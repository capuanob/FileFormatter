package GUI;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Bailey Capuano
 * This class generates a JPanel to have a textField and label for FormPanel formatting.
 */
public class InputPanel extends JPanel {
	private JLabel label;
	private JTextField textField;
	
	/**
	 * This function generates a proper label, text-field, and lays them out.
	 * @param labelText: Text to be included in JLabel label.
	 */
	public InputPanel(String labelText, boolean isEditable) {
		label = new JLabel(labelText);
		textField = new JTextField(20);
		
		textField.setEditable(isEditable);
		
		setLayout(new FlowLayout(FlowLayout.TRAILING)); // Allows for adjacent JComponents.
		
		add(label);
		add(textField);
	}
	
	/**
	 * 
	 * @return textField's content.
	 */
	public String getText() {
		return textField.getText();
	}
	
	/**
	 * 
	 * @param text: Set's textField's text to parameter.
	 */
	public void setText(String text) {
		textField.setText(text);
	}
}
