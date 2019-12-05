package PageFormater;

import java.lang.String;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Alignment extends Format {

	private static final long serialVersionUID = 1L;

	private alignTypes inputAlignment;

	public enum alignTypes {
        LEFT, CENTER, RIGHT, CTITLE
    }

	private int lineLength;

	public Alignment( alignTypes myAlignment) {
        switch ( myAlignment) {
        
            case LEFT:
            case CENTER:
            case RIGHT:
                this.inputAlignment = myAlignment;
                break;
            default:
                throw new IllegalArgumentException("invalid justification arg.");
        }
        this.lineLength = 80;
    }

    public StringBuffer format( Object inputStrings, StringBuffer outputStrings, FieldPosition ignore)
    {
        String inputString = inputStrings.toString();

        List<String> finalInput = new ArrayList<String>();
        
        for ( int x = 0; x < inputString.length(); x = x + lineLength) 
        {
            int y = Math.min( x + lineLength, inputString.length());
            finalInput.add( inputString.substring( x, y));
        }
        ListIterator<String> listItr = finalInput.listIterator();
         
        while (listItr.hasNext()) 
        {
            String expected = listItr.next();
             
            //Get the spaces in the right place.
            switch ( inputAlignment) {
                case RIGHT:
                	for (int i = 0; i < lineLength - expected.length(); i++){
            			outputStrings.append(' ');
                	}
                    outputStrings.append(expected);
                    break;
                case CENTER:
                    int toAdd = lineLength - expected.length();
                    for (int i = 0; i < toAdd / 2; i++){
            			outputStrings.append(' ');
                	}
                    outputStrings.append(expected);
                    for (int i = 0; i < toAdd - toAdd / 2; i++){
            			outputStrings.append(' ');
                	}
                    break;
                case LEFT:
                    outputStrings.append(expected);
                    for (int i = 0; i < lineLength - expected.length(); i++){
            			outputStrings.append(' ');
                	}
                    break;
                }
                outputStrings.append("\n");
        }
        return outputStrings;
    }

    String format(String inputString) {
        return format( inputString, new StringBuffer(), null).toString();
    }

    public static void main(String[] args) 
    {
        String sampleText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt "
                + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris "
                + "nisi ut aliquip ex ea commodo consequat.";
     
        Alignment util = new Alignment( alignTypes.RIGHT);
        System.out.println( util.format(sampleText) );
    }

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		// TODO Auto-generated method stub
		return null;
	}

}




