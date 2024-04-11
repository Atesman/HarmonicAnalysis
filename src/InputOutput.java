import java.util.ArrayList;
import java.util.List;

public class InputOutput {

	
	static public void printScaleName(String[] scaleInfo) {
		
		System.out.println("Scale Name:\n");
		
		if(scaleInfo[0].isEmpty()) {
			System.out.println("Unknown");
		}
		
		else {
			System.out.println(scaleInfo[0]);
			
			if(scaleInfo[1].isEmpty()) {
				System.out.println("\n");
			}
			else {
				System.out.println(scaleInfo[1] + "\n");
			}
		}
		
	}//end scaleInfo
	
	
	static public void printNoteLocations(List<String> noteDegreeNames) {
		
		System.out.println("\nScale Intervals:\n");
		
		for(String name : noteDegreeNames) {
			System.out.print(name + " ");
		}
		System.out.println("\n\n");
		
	}//end printNoteLocations
	
	
	static public void printAllChords(List<ArrayList<String>> allChordsNamedByPitch, List<ArrayList<String>> allChordsNamedNumerically) {
		
		System.out.println("Chords:\n");
		printChordsByMode(allChordsNamedByPitch);
		System.out.println("\n\nRoman Numerals:\n");
		printChordsByMode(allChordsNamedNumerically);
		System.out.println();

	}// end printAllChords
	
	
	private static void printChordsByMode(List<ArrayList<String>> chordList) {
		
		for (List<String> mode : chordList) {
			for(int i=0; i < mode.size(); i++) {
			    if( (mode.size() - i) == 1) {
			        System.out.format("%-10s %n", mode.get(i));
			    }
			    else {
			        System.out.format("%-10s", mode.get(i));
			    }
			}
	    }
	}//end printChordsByMode
	
}//end class InputOutput
