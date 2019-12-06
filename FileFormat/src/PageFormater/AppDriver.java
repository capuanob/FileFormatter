package PageFormater;


import java.awt.List;
import java.util.ArrayList;

import PageFormater.*;
import PageFormater.Alignment.alignTypes;

public class AppDriver {
	
	public static ArrayList<String> Driver(String str) {
		
		Alignment aligner;
		Spacing spacer;
		
		ArrayList<String> formattedLines = new ArrayList<String>();
		
		//Original text in ArrayList
		String text = str;
		text += "\n";
		//-----------------------------------
		//text and commands will be separated by flags
		//in the middle of paragraphs
		ArrayList<String> textList = new ArrayList<String>();
		ArrayList<ArrayList<Character>> commandList = new ArrayList<ArrayList<Character>>();
		
		//initialize first List in ArrayList
		textList.add("");
		commandList.add(new ArrayList<Character>());
		
		//showing the previous data type
		Boolean isString = false;
		Boolean isCommand = false;
		
		int i = 0; //tracking original string
		int j = 0; //tracking block (arraylist of string)
		while (i < text.length() - 1) {
			
			//we separate text when we get a new paragraph
			//after a newline, if its a "-*" following a newline, its a command
			
			if (text.charAt(i) == '-' && !isCommand && !isString) {
				commandList.get(j).add(text.charAt(i+1));
				isCommand = true;
			}
			//new line after command
			else if (text.charAt(i) == '\n' && isCommand) {
				isCommand = false;
			}
			//new line after string, separate
			else if (text.charAt(i) == '\n' && !isCommand && isString) {
				commandList.add(new ArrayList<Character>());
				textList.add("");
				j ++;
				isCommand = false;
				isString = false;
			}
			//string
			else if (text.charAt(i) != '\n' && !isCommand){
				String temp = textList.get(j);
				temp += text.charAt(i);
				textList.set(j, temp);
				isString = true;
			}
			i ++;
		}//end of while
		
		
		System.out.println(commandList);
		System.out.println("-----");
		System.out.println(textList);
		
		int a = 0; //tracking block #
		char choice;
		while (a < commandList.size()) {
			int b = 0; //tracking individual block command elements
			while (b < commandList.get(a).size()) {
				choice = commandList.get(a).get(b);
				switch (choice) {
					//Alignment
					case 'l':
						//left justification
						aligner = new Alignment(alignTypes.LEFT);
							formattedLines.add(aligner.format(textList.get(a)));
						break;
					case 'r':
						//right justification
						aligner = new Alignment(alignTypes.RIGHT);
						formattedLines.add(aligner.format(textList.get(a)));
						break;
					case 'c':
						//center justification
						aligner = new Alignment(alignTypes.CENTER);
						formattedLines.add(aligner.format(textList.get(a)));
						break;
					case 't':
						//centered title
						aligner = new Alignment(alignTypes.CTITLE);
						formattedLines.add(aligner.format(textList.get(a)));
						break;
						
					//Spacing
					case 's':
						//single space
						spacer = new Spacing(textList);
						ArrayList<String> formattedParagaph =  spacer.singleSpace();
						formattedLines.add(formattedParagaph.get(a));
						break;
					case 'd':
						//double space
						spacer = new Spacing(textList);
						ArrayList<String> formattedParagraph = spacer.doubleSpace();
						formattedLines.add(formattedParagraph.get(a));
						break;
						
					//Indentation
					case 'i':
						//first line 5 spaces
						break;
					case 'b':
						//multiple line 10 spaces
						break;
					case 'n':
						//remove indentation
						break;
					case '2':
						//create 2 columns, 35 char each, 10 char padding
					case '1':
						//create 1 column
						break;
					case 'e':
						//insert a blank line
						break;
					default:
						break;
				}//end of switch
				b ++;
			}
			a++;

		}//end of while
		
		return formattedLines;
	} //end driver
	
	/**
	 * This function merges lines to ensure all lines are 80 characters long (when possible). It also reduces lines longer than 80 characters to at most 80 characters
	 * @param lines
	 * @return
	 */
	public static ArrayList<String> preProcess(String fileBuffer) {
		ArrayList<String> lines = new ArrayList<String>(); 
		String[] words = fileBuffer.split(" "); // All words in file
		
		// Account for original spaces between words, as whitespace matters!
		for (int i = 0; i < words.length - 1; i++) {
			words[i].replace("\n", "");
			words[i] += " ";
		}
   		
   		int startIndex = 0;
   
		while (!fileBuffer.isEmpty()) {
			String validLine = "";
          
			for (int i = startIndex; i < words.length && validLine.length() + words[i].length() <= 80; i++) {
				validLine += words[i];
                startIndex++;
			}
          
			lines.add(validLine);
			fileBuffer = fileBuffer.substring(validLine.length());
		}
   
		return lines;
	}
}
