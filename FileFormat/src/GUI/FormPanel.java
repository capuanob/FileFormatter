package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * 
 * @author Bailey Capuano
 * This file handles user input of input path, output path, and file name.
 */
public class FormPanel extends JPanel {
	
	private InputPanel inputPath;
	private InputPanel outputPath;
	private InputPanel newFileName;
	
	private JButton loadInputFile;
	private JButton loadOutputPath;
	private JButton convertBtn;
	
	private File loadedFile = null;
	private FormListener formListener;
	private StringListener stringListener;
	
	/**
	 *  Sets local formListener property
	 * @param formListener: The new formListener to use
	 */
	
	public void setStringListener(StringListener stringListener) {
		this.stringListener = stringListener;
	}
	
	public void setFormListener(FormListener formListener) {
		this.formListener = formListener;
	}
	
	/**
	 * Default constructor
	 */
	public FormPanel() {
		
		// Sets up panel to take up a preferable 350px. 
		Dimension dim = getPreferredSize();
		dim.width = 400;
		setPreferredSize(dim);
		
		
		inputPath = new InputPanel("Input path: ", false);
		outputPath = new InputPanel("Output path: ", false);
		newFileName = new InputPanel("New file name: ", true);
		
		loadInputFile = new JButton("Load");
		loadOutputPath = new JButton("Load");
		convertBtn = new JButton("Convert!");
		
		
		loadInputFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileLoader fl = new FileLoader((JButton) e.getSource());
				loadedFile = fl.getInputFile();
				
				if (!loadedFile.canRead()) { // File privileges prevent us from using this file.
					loadedFile = null;
				}
				
				if (loadedFile != null) {
					inputPath.setText(loadedFile.getPath());
				} else {
					inputPath.setText("");
				}
				
				if (stringListener != null) {
					
					Path filePath = Paths.get(loadedFile.getAbsolutePath());
					List<String> lines = null;
					
					try {
						lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					stringListener.inputFileChosen(lines);
				}
			}
		});
		
		loadOutputPath.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PathLoader fl =  new PathLoader((JButton) e.getSource());
				String oPath = fl.getOutputPath();
				
				if (outputPath != null) {
					outputPath.setText(oPath);
				} else {
					outputPath.setText("");
				}
			}
		});
		/**
		 * On click, convertBtn generates an anonymous ActionListener class that passes formListener a new FormEvent
		 * containing all required information to handle file I/O
		 */
		convertBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String oPathString = outputPath.getText();
				
				if (loadedFile != null && !oPathString.isEmpty()) {
					String fileName = (newFileName.getText().isEmpty()) ? "formatted_" + loadedFile.getName() : newFileName.getText();
					FormEvent ev = new FormEvent(this, loadedFile, oPathString, fileName);
					
					if (formListener != null) {
						formListener.formEventOccurred(ev);
					}
				}
			}
			
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("Formatting Information");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		

		//// First Group ////
		gc.weightx = 1;
		gc.weighty = 0.1;
	
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		
		add(inputPath, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		
		add(loadInputFile, gc);
		
		//// Second Group ////
		gc.weightx = 1;
		gc.weighty = 0.1;
	
		gc.gridx = 0;
		gc.gridy = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		
		add(outputPath, gc);
		
		gc.gridx = 0;
		gc.gridy = 3;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		
		add(loadOutputPath, gc);
		
		//// Third Group ////
		gc.weightx = 1;
		gc.weighty = 0.1;
	
		gc.gridx = 0;
		gc.gridy = 4;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		
		add(newFileName, gc);

		//// Fourth Group ////
		gc.weightx = 1;
		gc.weighty = 3;
		
		gc.gridx = 0;
		gc.gridy = 6;
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.HORIZONTAL;
		
		add(convertBtn, gc);
	}
}
