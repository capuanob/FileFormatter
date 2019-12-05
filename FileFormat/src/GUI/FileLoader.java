package GUI;

import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileLoader {
	protected JFileChooser fileChooser;
	protected JComponent parent;

	public File getInputFile() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT file", "txt"); // Only shows .txt files in search...
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false); // We only want to take in .txt files.
		
		int returnVal = fileChooser.showOpenDialog(parent);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		} else {
			return null;
		}
		
	}

	public FileLoader(JComponent parent) {
		fileChooser = new JFileChooser();
		this.parent = parent;
	}
}