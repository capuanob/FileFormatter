package PageFormater;

import java.util.ArrayList;

public class Spacing {
	private ArrayList<String> text;
	
	public Spacing(ArrayList<String> str) {
		text = str;
	}
	
	public ArrayList<String> singleSpace() {
		int i = 1;
		while (i < text.size()) {
			if (!text.get(i).equals("\n")) {
				text.remove(i);
			}
			i ++;
		}
		return text;
	}
	
	public ArrayList<String> doubleSpace(int x) {
		text.add(x, "\n");
		return text;
	}
}
