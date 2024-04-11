
public class HarmonicAnalysisRunner {

	static final int TONE_COUNT = 12;
	
	// Scales for testing purposes
 	static int[] major = new int[] {1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1};
 	static int[] minor = new int[] {1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0};
 	static int[] test = new int[] {1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1};
	
	private static String userInput;
	private static int tonic;
	
	
	public static void main(String[] args) {
		
		userInput = String.join(" ", args);
		HarmonicAnalysis harmonicAnalysis = new HarmonicAnalysis();
		
		if(args.length == 0) {
			harmonicAnalysis.start(0, test);
		}
		else {
			if(!validUserInput()) {
				harmonicAnalysis.start(0, major);
			}
			else {
				assignTonic();
				String userGivenScale = userInput.substring(0, TONE_COUNT);
				int[] scale = translateInputToScale(userGivenScale);
				harmonicAnalysis.start(tonic, scale);
			}
		}
	}//end main
	
	
	private static boolean validUserInput() {
		
	    if(userInput.matches("[01]{12}")) {
	    	return true;
	    }
	    else if(userInput.matches("^[01]{12} ([0-9]|1[01])$")){
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}//end validUserInput
	
	
	private static void assignTonic() {
		
		if(userInput.length() == TONE_COUNT) {
			tonic = 0;
		}
		else {
			String givenKey = userInput.substring(TONE_COUNT + 1);
			tonic = Integer.parseInt(givenKey);
		}
		
	}//end assignTonic
	
	
	private static int[] translateInputToScale(String userGivenScale) {
		
		int[] scale = new int[userGivenScale.length()];
		
	    for (int i = 0; i < userGivenScale.length(); i++) {
	        scale[i] = Character.getNumericValue(userGivenScale.charAt(i));
	    }
	    return scale;
		
	}//end translateInputToScale
	
}// end class HarmonicAnalysisRunner
