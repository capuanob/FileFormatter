package GUI;

import java.awt.BorderLayout;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFrame;

import PageFormater.AppDriver;

/**
 * 
 * @author Bailey Capuano
 * In terms of MVC, MainFrame serves as the Controller of the various views required for File Formatter to function.
 * Handles events and interactions between various view's action listeners. 
 */

public class MainFrame extends JFrame {
	
	private TextPanel textPanel;
	private FormPanel formPanel;
	private String inputFileContents;
	
	/**
	 *  Default constructor for main frame.
	 */
	public MainFrame() {
		super("File Formatter"); // In addition to setting up the frame, this also establishes the window's name. 
		
		setLayout(new BorderLayout());
		
		textPanel = new TextPanel();
		formPanel = new FormPanel();
		inputFileContents = null;
		
		//TODO: Establish a StringListener when inputPath has been written.
		
		formPanel.setStringListener(new StringListener() {
			public void inputFileChosen(String content) {
				if (content != null) {
					inputFileContents = content;
					textPanel.appendText(inputFileContents);
				}
			}
		});
		
		/**
		 * Listens for creation of a FormEvent in FormPanel. Will then call formatting functionality. 
		 */
		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				String outputPath = e.getOutputPath();
				String newFileName = e.getNewFileName();
				
				System.out.println(outputPath);
				System.out.println(newFileName);
				
				if (inputFileContents != null) { // If we have access to the lines of file input...
					
					//String[] paragraphs = inputFileContents.split("\n"); // Flags are own paragraphs
					
					/*
					for (String paragraph: paragraphs) {
						
						ArrayList<String> formattedParagraph = AppDriver.preProcess(paragraph);
						System.out.println(formattedParagraph);						
					}
					*/
					
					ArrayList<String> formattedLines = AppDriver.Driver(inputFileContents);
					
					textPanel.clearContent();
					
					for (String formattedLine: formattedLines) {
						textPanel.appendText(formattedLine);
					}
					//OUTPUT TO FILE
					
					
					try {
						File outputFile = new File(outputPath, newFileName + ".txt");
						outputFile.createNewFile();
						Path filePath = Paths.get(outputPath + "/" + newFileName + ".txt");
						Files.write(filePath, formattedLines, StandardCharsets.UTF_8);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
				}
				///TODO: CALL TO MAIN FUNCTIONALITY GOES HERE, THEN SET A NEW LIST<STRING> EQUAL TO RETURN
				
				///TODO: POPULATE TEXTPANEL WITH FORMATTED CONTENT
				
				///TODO: CREATE FILE AT OUTPUT PATH AND SAVE IT
				
				///TODO: ALERT USER OF SUCCESSFUL FILE CREATION
			}
		});
		
		add(formPanel, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		
		setSize(950, 600); // Default window size, can be expanded or closed. 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminates program and clears memory on hitting X.
		setVisible(true);
	}
}
