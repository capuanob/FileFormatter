package PageFormater;

import java.util.ArrayList;

public class Spacing {
	private ArrayList<String> text;
	
	public Spacing(ArrayList<String> str) {
		text = str;
	}
	
	public ArrayList<String> singleSpace() {
		int i = 0;
		while (i < text.size()) {
			if (text.get(i).replaceAll("\\s+","").isEmpty()) {
				text.remove(i);
			} else {
				i++;
			}
		}
		return text;
	}
	
	public ArrayList<String> doubleSpace() {
		int i = 1;
		
		while (i < text.size()) {
			text.add(i, "\n");
			i += 2;
		}
		return text;
	}
}
