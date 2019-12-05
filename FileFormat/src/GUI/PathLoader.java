package GUI;

import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFileChooser;

public class PathLoader extends FileLoader {

	public PathLoader(JComponent parent) {
		super(parent);
	}
	
	public String getOutputPath() {
		
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fileChooser.showSaveDialog(parent);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().getPath();
		} else {
			return null;
		}
		
	}
}