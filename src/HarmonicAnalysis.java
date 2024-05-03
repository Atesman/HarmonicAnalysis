import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HarmonicAnalysis {

	// Data pertaining to the musical system
	static final int TONE_COUNT = 12;
	static final int MAJOR_THIRD = 4;
		
	// Dictionaries
	static String[] NOTE_NAMES;
	static final String[] BOTH_NOTE_NAMES = new String[] {"C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B"};
	static final String[] SHARP_NOTE_NAMES = new String[] {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	static final String[] FLAT_NOTE_NAMES = new String[] {"C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B"};
	
	static final String[] MAJOR_ROMAN_NUMERALS = new String[] {"I", "bII", "II", "bIII", "III", "IV", "bV", "V", "bVI", "VI", "bVII", "VII"};
	static final String[] MINOR_ROMAN_NUMERALS = new String[] {"i", "bii", "ii", "biii", "iii", "iv", "bv", "v", "bvi", "vi", "bvii", "vii"};
	static final String[] pitchBasedSuffixes = new String[]{"", "7", "M7", "m", "m7", "mM7", "dim", "m7b5", "dim7", "dim(M7)", "+", "+7", "+M7"};
    static final String[] numericalSuffixes = new String[]{"", "7", "M7", "", "7", "M7", "(b5)", "m7b5", "dim7", "(b5)M7", "+", "+7", "+M7"};
    static final List<List<Integer>> CHORD_INTERVAL_DICTIONARY;
    static {List<List<Integer>> tempChordList = new ArrayList<List<Integer>>();
    	//Major
        tempChordList.add(Arrays.asList(0, 4, 7));
        tempChordList.add(Arrays.asList(0, 4, 7, 10));
        tempChordList.add(Arrays.asList(0, 4, 7, 11));
        //Minor
        tempChordList.add(Arrays.asList(0, 3, 7));
        tempChordList.add(Arrays.asList(0, 3, 7, 10));
        tempChordList.add(Arrays.asList(0, 3, 7, 11));
        //Diminished
        tempChordList.add(Arrays.asList(0, 3, 6));
        tempChordList.add(Arrays.asList(0, 3, 6, 10));
        tempChordList.add(Arrays.asList(0, 3, 6, 9));
        tempChordList.add(Arrays.asList(0, 3, 6, 11));
        //Augmented
        tempChordList.add(Arrays.asList(0, 4, 8));
        tempChordList.add(Arrays.asList(0, 4, 8, 10));
        tempChordList.add(Arrays.asList(0, 4, 8, 11));
        CHORD_INTERVAL_DICTIONARY = tempChordList;
    }
    
	// Variables used in each instance of analysis
 	private int key;
	private int[] mainScale;
	private int[] currentMode;
	private List<Integer> noteLocations = new ArrayList<Integer>();
	private List<String> tempPitchBasedNames = new ArrayList<String>();
	private List<String> tempNumericalNames = new ArrayList<String>();
	private List<ArrayList<String>> allChordsNamedByPitch = new ArrayList<ArrayList<String>>();
	private List<ArrayList<String>> allChordsNamedNumerically = new ArrayList<ArrayList<String>>();
	private List<String> noteDegreeNames;			//?
	
	
	public void start(int key, int[] scale, int sharpOrFlat) {
		
		assignKeyAndScale(key, scale);
		assignNoteNames(sharpOrFlat);
		findScaleNoteLocations();
		findAvailableChords();
		
		InputOutput.printScaleName(DetectScale.detectScale(scale));
		noteDegreeNames = ScaleDegreeNaming.start(scale);
		InputOutput.printNoteLocations(noteDegreeNames);
		InputOutput.printAllChords(allChordsNamedByPitch, allChordsNamedNumerically);
		
	}//end start
	
	
	private void assignKeyAndScale(int key, int[] scale) {
		
		this.key = key;
		this.mainScale = scale;
		this.currentMode = this.mainScale;
		
	}//end assignKeyAndScale
	
	
	private void assignNoteNames(int sharpOrFlat) {
		
		if(sharpOrFlat == 1) {
			NOTE_NAMES = SHARP_NOTE_NAMES;
			return;
		}
		if(sharpOrFlat == 2) {
			NOTE_NAMES = FLAT_NOTE_NAMES;
			return;
		}
		else {
			NOTE_NAMES = BOTH_NOTE_NAMES;
		}
		
	}//end assignNoteNames

	
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
		
		int chordNameSuffixIndex = 0;
		for(List<Integer> potentialChord : CHORD_INTERVAL_DICTIONARY) {
			
			if(currentModeContainsChord(potentialChord)) {
				String[] chordNames = nameChord(degree, potentialChord, chordNameSuffixIndex);
				tempPitchBasedNames.add(chordNames[0]);
				tempNumericalNames.add(chordNames[1]);
				
			}
			chordNameSuffixIndex++;
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
	
	
	private String[] nameChord(Integer degree, List<Integer> chord, int suffixIndex) {
		
		String tempString = "";
		String[] tempArray = new String[2];
		
		tempString = NOTE_NAMES[(degree + key) % TONE_COUNT];
		tempString = tempString + pitchBasedSuffixes[suffixIndex];
		tempArray[0] = tempString;
		tempString = "";
	
		if(chordIsMajor(chord)) {
			tempString = MAJOR_ROMAN_NUMERALS[degree];
		}
		else {
			tempString = MINOR_ROMAN_NUMERALS[degree];
		}
	
		tempString = tempString + numericalSuffixes[suffixIndex];
		tempArray[1] = tempString;

		return tempArray;
		
	}//end nameChord
	
	
	private boolean chordIsMajor(List<Integer> chord) {
		
		if(chord.contains(MAJOR_THIRD)) {
			return true;
		}
		return false;
		
	}//end chordIsMajor
	
	
	private void addNewChordsToList() {
		
		allChordsNamedByPitch.add(new ArrayList<>(tempPitchBasedNames));
	    allChordsNamedNumerically.add(new ArrayList<>(tempNumericalNames));
	    tempPitchBasedNames.clear();
	    tempNumericalNames.clear();
		
	}//end addNewChordsToList

}//end class HarmonicAnalysis
