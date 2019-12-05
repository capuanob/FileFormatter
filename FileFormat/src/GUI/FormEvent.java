package GUI;

import java.io.File;
import java.util.EventObject;

/**
 * 
 * @author Bailey Capuano
 * This class is used to transmit data between FormPanel and MainFrame without high coupling. 
 */
public class FormEvent extends EventObject {
	
	private File inputFile;
	private String outputPath;
	private String newFileName;
	
	
	/**
	 * Default constructor
	 * @param source: Generic object that generated this event.
	 */
	public FormEvent(Object source) {
		super(source);
		
	}
	
	/// Getters and Setters ///
	
	public File getInputFile() {
		return inputFile;
	}

	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	/**
	 * Convenience initializer, used to store parameters into their respective local attributes.
	 * @param source: Object that generated this event
	 * @param inputFile: File to be formatted
	 * @param outputPath: Where to store the new file on the user's computer
	 * @param newFileName: What to call the computer, is an optional parameter.
	 */
	public FormEvent(Object source, File inputFile, String outputPath, String newFileName) {
		super(source);
		
		this.inputFile = inputFile;
		this.outputPath = outputPath;
		this.newFileName = newFileName;
	}
	
}
