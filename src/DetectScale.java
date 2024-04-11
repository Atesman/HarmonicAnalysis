import java.util.HashMap;
import java.util.Map;

public class DetectScale {

	 private static Map<String, String[]> scaleDictionary = new HashMap<>();

	    static {
	    	
	    	//scaleDictionary.put("", new String[]{"", ""});
	    	
	    //PENTATONIC

	    	scaleDictionary.put("101010010100", new String[]{"Major Pentatonic", ""});
			scaleDictionary.put("100101010010", new String[]{"Minor Pentatonic", ""});

	    //HEXATONIC
	    	
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
	        
	    //OCTATONIC
	        
	        scaleDictionary.put("101101101101", new String[]{"Diminished", "Whole-Half Diminished"});
	        scaleDictionary.put("110110110110", new String[]{"Dominant Diminished", "Half-Whole Diminished"});
	             
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
	