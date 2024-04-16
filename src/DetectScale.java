import java.util.HashMap;
import java.util.Map;

public class DetectScale {

	 private static Map<String, String[]> scaleDictionary = new HashMap<>();

	    static {
	    	
	    //PENTATONIC

	    	scaleDictionary.put("101010010100", new String[]{"Major Pentatonic", ""});
			scaleDictionary.put("100101010010", new String[]{"Minor Pentatonic", ""});

	    //HEXATONIC
	    	
			scaleDictionary.put("100110011001", new String[]{"Augmented", ""});
			scaleDictionary.put("101110010100", new String[]{"Major Blues", ""});
			scaleDictionary.put("100101110010", new String[]{"Minor Blues", ""});
			scaleDictionary.put("101011010100", new String[]{"Major Hexatonic", ""});
			scaleDictionary.put("101101010010", new String[]{"Minor Hexatonic", ""});
			scaleDictionary.put("101010100110", new String[]{"Prometheus", "Mystic chord: 1-#4-b7-3-6-2"});
	    	scaleDictionary.put("101010101010", new String[]{"Whole Tone", ""});

	    //HEPTATONIC

	    	//Major
	        scaleDictionary.put("101011010101", new String[]{"Major", "Ionian - 1st Mode of the Major Scale"});
	        scaleDictionary.put("101101010110", new String[]{"Dorian", "2nd Mode of the Major Scale"});
	        scaleDictionary.put("110101011010", new String[]{"Phrygian", "3rd Mode of the Major Scale"});
	        scaleDictionary.put("101010110101", new String[]{"Lydian", "4th Mode of the Major Scale"});
	        scaleDictionary.put("101011010110", new String[]{"Mixolydian", "5th Mode of the Major Scale"});
	        scaleDictionary.put("101101011010", new String[]{"Natural Minor", "Aeolian - 6th Mode of the Major Scale"});
	        scaleDictionary.put("110101101010", new String[]{"Locrian", "7th Mode of the Major Scale"});
	        
	        //Harmonic Minor
	        scaleDictionary.put("101101011001", new String[]{"Harmonic Minor", ""});
	        scaleDictionary.put("110101100110", new String[]{"Locrian Natural 6", "2nd Mode of the Harmonic Minor Scale"});
	        scaleDictionary.put("101011001101", new String[]{"Ionian #5", "3rd Mode of the Harmonic Minor Scale"});
	        scaleDictionary.put("101100110110", new String[]{"Dorian #11", "4th Mode of the Harmonic Minor Scale / aka Ukrainian Dorian or Romanian Minor"});
	        scaleDictionary.put("110011011010", new String[]{"Phyrgian Dominant", "5th Mode of the Harmonic Minor Scale"});
	        scaleDictionary.put("100110110101", new String[]{"Lydian #2", "6th Mode of the Harmonic Minor Scale"});
	        scaleDictionary.put("110110101100", new String[]{"Altered Diminished", "7th Mode of the Harmonic Minor Scale / aka Super Locrian bb7"});
	        
	        //Melodic Minor
	        scaleDictionary.put("101101010101", new String[]{"Melodic Minor", ""});
	        scaleDictionary.put("110101010110", new String[]{"Dorian b9", "2nd Mode of the Melodic Minor Scale"});
	        scaleDictionary.put("101010101101", new String[]{"Lydian Augmented", "3rd Mode of the Melodic Minor Scale"});
	        scaleDictionary.put("101010110110", new String[]{"Lydian Dominant", "4th Mode of the Melodic Minor Scale / aka Acoustic or Overtone"});
	        scaleDictionary.put("101011011010", new String[]{"Mixolydian b6", "5th Mode of the Melodic Minor Scale"});
	        scaleDictionary.put("101101101010", new String[]{"Aeolian b5", "6th Mode of the Melodic Minor Scale"});
	        scaleDictionary.put("110110101010", new String[]{"Altered", "7th Mode of the Melodic Minor Scale / aka Altered Dominant, Super Locrian, or Diminished Whole Tone"});
	        
	        //Harmonic Major
	        scaleDictionary.put("101011011001", new String[]{"Harmonic Major", ""});
	        scaleDictionary.put("101101100110", new String[]{"Dorian b5", "2nd Mode of the Harmonic Major Scale"});
	        scaleDictionary.put("110110011010", new String[]{"Phrygian b4", "3rd Mode of the Harmonic Major Scale"});
	        scaleDictionary.put("101100110101", new String[]{"Melodic Minor #4", "4th Mode of the Harmonic Major Scale"});
	        scaleDictionary.put("110011010110", new String[]{"Mixolydian b2", "5th Mode of the Harmonic Major Scale"});
	        scaleDictionary.put("100110101101", new String[]{"Lydian Augmented #2", "6th Mode of the Harmonic Major Scale"});
	        scaleDictionary.put("110101101100", new String[]{"Locrian bb7", "7th Mode of the Harmonic Major Scale"});
	        
	        //Double Harmonic Major
	        scaleDictionary.put("110011011001", new String[]{"Double Harmonic Major", "aka Byzantine or Gypsy Major"});
	        scaleDictionary.put("100110110011", new String[]{"Lydian #2 #6", "2nd Mode of the Double Harmonic Major Scale"});
	        scaleDictionary.put("110110011100", new String[]{"Ultraphrygian", "3rd Mode of the Double Harmonic Major Scale"});
	        scaleDictionary.put("101100111001", new String[]{"Double Harmonic Minor", "4th Mode of the Double Harmonic Major Scale / aka Hungarian Minor or Gypsy Minor"});
	        scaleDictionary.put("110011100110", new String[]{"Oriental", "5th Mode of the Double Harmonic Major Scale"});
	        scaleDictionary.put("100111001101", new String[]{"Ionian #2 #5", "6th Mode of the Double Harmonic Major Scale"});
	        scaleDictionary.put("111001101100", new String[]{"Locrian bb3 bb7", "7th Mode of the Double Harmonic Major Scale"});
	        
	    //OCTATONIC
	        
	        scaleDictionary.put("101101101101", new String[]{"Diminished", "Whole-Half Diminished"});
	        scaleDictionary.put("110110110110", new String[]{"Dominant Diminished", "Half-Whole Diminished"});
	        scaleDictionary.put("101011011101", new String[]{"Major Sixth Diminished", "Barry Harris's Sixth Diminished Scale"});
	        scaleDictionary.put("101101011101", new String[]{"Minor Sixth Diminished", "Barry Harris's Sixth Diminished Scale"});
	             
	    //DODECATONIC

	        scaleDictionary.put("111111111111", new String[]{"Chromatic", ""});
	        
	    }
	    
	    
    public static String[] detectScale(int[] scale) {
    	
    	String[] unrecognizedScaleName = {"",""};
    	
    	for(Map.Entry<String, String[]> potentialScale: scaleDictionary.entrySet()) {
    		
    		if(potentialScale.getKey().contentEquals(translateScale(scale))){
    			return potentialScale.getValue();   
    		}
    	}
    	
    	return unrecognizedScaleName;
    	
    }//end detectScale
	
    
    private static String translateScale(int[] originalScale) {
    	
    	StringBuilder translatedScale = new StringBuilder();
    	
    	for(int degree : originalScale) {
    		translatedScale.append(degree);
    	}
    	
    	return translatedScale.toString();
    	
    }//end translateScale
	    
	
}//end class DetectScale
	