import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HarmonicAnalysis {

	// Data pertaining to the musical system
	static final int TONE_COUNT = 12;
	static final int MAJOR_THIRD = 4;
		
	// Dictionaries									     0    1     2    3     4    5      6      7     8    9    10   11
	static final String[] SCALE_DEGREES = new String[] {"1", "b2", "2", "b3", "3", "4", "#4/b5", "5", "b6", "6", "b7", "7"};
	static final String[] NOTE_NAMES = new String[] {"C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B"};
	static final String[] MAJOR_ROMAN_NUMERALS = new String[] {"I", "bII", "II", "bIII", "III", "IV", "bV", "V", "bVI", "VI", "bVII", "VII"};
	static final String[] MINOR_ROMAN_NUMERALS = new String[] {"i", "bii", "ii", "biii", "iii", "iv", "bv", "v", "bvi", "vi", "bvii", "vii"};
	static final Map<List<Integer>, String> NUMERICAL_CHORD_DICTIONARY;
    static {
        Map<List<Integer>, String> tempChordMap = new LinkedHashMap<>();
        tempChordMap.put(Arrays.asList(0, 4, 7),	"");
        tempChordMap.put(Arrays.asList(0, 4, 7, 10),"7");
        tempChordMap.put(Arrays.asList(0, 4, 7, 11),"M7");
        tempChordMap.put(Arrays.asList(0, 3, 7), 	"");
        tempChordMap.put(Arrays.asList(0, 3, 7, 10),"7");
        tempChordMap.put(Arrays.asList(0, 3, 7, 11),"M7");
        tempChordMap.put(Arrays.asList(0, 3, 6), 	"(b5)");
        tempChordMap.put(Arrays.asList(0, 3, 6, 10),"m7b5");
        tempChordMap.put(Arrays.asList(0, 3, 6, 9),	"dim7");
        tempChordMap.put(Arrays.asList(0, 4, 8), 	"+");
        tempChordMap.put(Arrays.asList(0, 4, 8, 10),"+7");
        tempChordMap.put(Arrays.asList(0, 4, 8, 11),"+M7");
        NUMERICAL_CHORD_DICTIONARY = Collections.unmodifiableMap(tempChordMap);
    }
    static final Map<List<Integer>, String> PITCH_CHORD_DICTIONARY;
    static {
        Map<List<Integer>, String> tempChordMap = new LinkedHashMap<>();
        tempChordMap.put(Arrays.asList(0, 4, 7),	"");
        tempChordMap.put(Arrays.asList(0, 4, 7, 10),"7");
        tempChordMap.put(Arrays.asList(0, 4, 7, 11),"M7");
        tempChordMap.put(Arrays.asList(0, 3, 7), 	"m");
        tempChordMap.put(Arrays.asList(0, 3, 7, 10),"m7");
        tempChordMap.put(Arrays.asList(0, 3, 7, 11),"mM7");
        tempChordMap.put(Arrays.asList(0, 3, 6), 	"(b5)");
        tempChordMap.put(Arrays.asList(0, 3, 6, 10),"m7b5");
        tempChordMap.put(Arrays.asList(0, 3, 6, 9),	"dim7");
        tempChordMap.put(Arrays.asList(0, 4, 8), 	"+");
        tempChordMap.put(Arrays.asList(0, 4, 8, 10),"+7");
        tempChordMap.put(Arrays.asList(0, 4, 8, 11),"+M7");
        PITCH_CHORD_DICTIONARY = Collections.unmodifiableMap(tempChordMap);
    }
	
    // Scales for testing purposes
 	static int[] major = new int[] {1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1};
 	static int[] minor = new int[] {1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0};
 	static int[] test = new int[] {1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1};
    
	// Variables used in each instance of analysis
	private int[] mainScale;
	private int[] currentMode;
	private ArrayList<Integer> noteLocations = new ArrayList<Integer>();
	private ArrayList<String> tempAlphaChords = new ArrayList<String>();
	private ArrayList<String> tempNumChords = new ArrayList<String>();
	private ArrayList<ArrayList<String>> chordsNamedTonally = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> chordsNamedNumerically = new ArrayList<ArrayList<String>>();
	
	
	public static void main(String[] args) {
		
		HarmonicAnalysis harmonicAnalysis = new HarmonicAnalysis();
		
		if(args.length > 0) {
			if(validUserInput(args[0])) {
				int[] scale = translateInputToScale(args[0]);
				harmonicAnalysis.start(scale);
			}
			else {
				harmonicAnalysis.start(major);
			}
		}
		else {
			harmonicAnalysis.start(major);
		}
	}//end main
	
	
	private static boolean validUserInput(String userInput) {
		
	    return userInput.matches("[01]{12}");
	  
	}//end validUserInput
	
	
	private static int[] translateInputToScale(String userInput) {
		
		int[] scale = new int[userInput.length()];
		
	    for (int i = 0; i < userInput.length(); i++) {
	        scale[i] = Character.getNumericValue(userInput.charAt(i));
	    }
	    return scale;
		
	}//end translateInputToScale
	
	
	private void start(int[] scale) {
		
		assignScale(scale);
		findScaleNoteLocations();
		printNoteLocations();
		findAvailableChords();
		printAllChords();
		
	}//end start
	
	
	private void assignScale(int[] scale) {
		
		this.mainScale = scale;
		this.currentMode = this.mainScale;
		
	}//end assignScale


	private void findScaleNoteLocations() {
		
		for(int i = 0; i < TONE_COUNT; i++) {
			if(noteAtLocation(i)) {
				this.noteLocations.add(i);
			}
		}
	}//end findNoteLocations
	
	
	private boolean noteAtLocation(int i) {
		
		if(currentMode[i] > 0) {
			return true;
		}
		return false;
		
	}//end noteAtLocations
	
	
	private void printNoteLocations() {
		
		System.out.println("\nScale Intervals:\n");
		
		for(Integer note : noteLocations) {
			System.out.print(SCALE_DEGREES[note] + " ");
		}
		System.out.println("\n\n");
		
	}//end printNoteLocations
	
	
	private void findAvailableChords() {
		
		for(Integer degree : noteLocations) {
			calculateCurrentMode(degree);
			findChordsFromCurrentRoot(degree);
			addNewChordsToList();
		}
	}//end findAvailableChords
	
	
	private void calculateCurrentMode(int newRoot) {

		int[] tempArray = new int[TONE_COUNT];
        System.arraycopy(this.mainScale, newRoot, tempArray, 0, (TONE_COUNT - newRoot));
        System.arraycopy(this.mainScale, 0, tempArray, (TONE_COUNT - newRoot), newRoot); 
        this.currentMode = tempArray;
		
	}//end calculateCurrentMode
	
	
	private void findChordsFromCurrentRoot(Integer degree) {
		
		for(Map.Entry<List<Integer>, String> potentialChord : NUMERICAL_CHORD_DICTIONARY.entrySet()) {
			
			if(currentModeContainsChord(potentialChord.getKey())) {
				String chordNameNum = nameChord(degree, potentialChord, 1);
				tempNumChords.add(chordNameNum);
			}
		}
		
		for(Map.Entry<List<Integer>, String> potentialChord : PITCH_CHORD_DICTIONARY.entrySet()) {
			
			if(currentModeContainsChord(potentialChord.getKey())) {
				String chordNameAlpha = nameChord(degree, potentialChord, 0);
				tempAlphaChords.add(chordNameAlpha);
			}
		}
		
	}//end findChordsFromCurrentRoot
	
	
	private boolean currentModeContainsChord(List<Integer> potentialChord) {
		
		for(Integer targetNote : potentialChord) {
			
			if( !(noteAtLocation(targetNote))) {
				return false;
			}
		}
		return true;
		
	}//end currentModeContainsChord
	
	
	private String nameChord(Integer degree, Map.Entry<List<Integer>, String> chord, int marker) {
		
		String tempString = "";
		
		
		if(marker==1) {
			
			if(chordIsMajor(chord)) {
				tempString = MAJOR_ROMAN_NUMERALS[degree];
			}
			else {
				tempString = MINOR_ROMAN_NUMERALS[degree];
			}
			
			if( (chord.getKey().size() == 3) && (chord.getKey().get(2) == 7) ) {
				return tempString;
			}
			
			tempString = tempString + chord.getValue();
			return tempString;
			
		}
		
		else {
			tempString = NOTE_NAMES[degree];
			tempString = tempString + chord.getValue();
			return tempString;
		}
	}//end nameChord
	
	
	private boolean chordIsMajor(Map.Entry<List<Integer>, String> chord) {
		
		if(chord.getKey().contains(MAJOR_THIRD)) {
			return true;
		}
		return false;
		
	}//end chordIsMajor
	
	
	private void addNewChordsToList() {
		
		chordsNamedTonally.add(new ArrayList<>(tempAlphaChords));
	    chordsNamedNumerically.add(new ArrayList<>(tempNumChords));
	    tempAlphaChords.clear();
	    tempNumChords.clear();
		
	}//end addNewChordsToList
	
	
	private void printAllChords() {
		
		System.out.println("Chords:\n");
		for (ArrayList<String> mode : chordsNamedTonally) {
			for(int i=0; i < mode.size(); i++) {

			    if( (mode.size() - i) == 1) {
			        System.out.format("%-10s %n", mode.get(i));
			    }
			    else {
			        System.out.format("%-10s", mode.get(i));
			    }
			}
	    }
		
		System.out.println("\n\nRoman Numerals:\n");
		
		for (ArrayList<String> mode : chordsNamedNumerically) {
			for(int i=0; i < mode.size(); i++) {
			    if( (mode.size() - i) == 1) {
			        System.out.format("%-10s %n", mode.get(i));
			    }
			    else {
			        System.out.format("%-10s", mode.get(i));
			    }
			}
	    }

	}// end printAllChords
	
}//end class HarmonicAnalysis
