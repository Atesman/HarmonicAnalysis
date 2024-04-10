import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScaleDegreeNaming {

	private static int[] scale;
	private static List<String> noteDegreeNames;
	
	@SuppressWarnings("serial")
	private static final HashMap<Integer, String> degreeSymbols = new HashMap<Integer, String>(){{
		put(1, "b2");
		put(2, "2");
		put(4, "3");
		put(5, "4");
		put(7, "5");
		put(9, "6");
		put(10, "b7");
		put(11, "7");
	}};
 	
	public static List<String> start(int[] inputScale) {
		
		scale = inputScale;
		noteDegreeNames = new ArrayList<String>();
		degreeNamingLogic(scale);
		return noteDegreeNames;
		
	}//end start
	
	
	private static void degreeNamingLogic(int[] scale) {
		
		for(int i= 0 ; i < scale.length; i++) {
			
			// Tonic is hard-coded
			if(i == 0) {
				noteDegreeNames.add("1");
				continue;
			}
			// #2/b3
			if((i == 3) && (scale[i] == 1)) {
				sharpTwoFlatThree();
				continue;
			}
			// Tritone
			if((i == 6) && (scale[i] == 1)) {
				tritone();
				continue;
			}
			// #5/b6
			if((i == 8) && (scale[i] == 1)) {
				sharpFiveFlatSix();
				continue;
			}
			
			// All other degrees
			if(scale[i] == 1) {
				noteDegreeNames.add(degreeSymbols.get(i));
				continue;
			}
		}
		
	}//end degreeNamingLogic
	
	
	private static void sharpTwoFlatThree() {
		
		// If Major 3rd is present
		if(scale[4] == 1) {
			noteDegreeNames.add("#2");
		}
		// If NO Major 3rd is present
		else {
			noteDegreeNames.add("b3");
		}
		
	}//end sharpTwoFlatThree
	
	
	private static void tritone() {
		
		//INCOMPLETE TRITONE LOGIC
		
		// If 4 = YES and 5 = NO
		if((scale[5] == 1) && (scale[7] == 0)) {
			noteDegreeNames.add("b5");
		}
		
		// If 4 = NO and 5 = YES
		else if((scale[5] == 0) && (scale[7] == 1)) {
			noteDegreeNames.add("#4");
		}			
		
		// If 4 = YES and 5 = YES
		else if((scale[5] == 1) && (scale[7] == 1)) {
			// If Major 3 is present at all
			if(scale[4] == 1) {
				noteDegreeNames.add("#4");
			}
			// If ONLY Minor 3rd is present
			else if(scale[3] == 1) {
				noteDegreeNames.add("b5");
			}
			//If NO 3rd present
			else {
				noteDegreeNames.add("b5");
			}
			//INCOMPLETE TRITONE LOGIC
		}			
		
		// If 4 = NO and 5 = NO
		else {
			noteDegreeNames.add("b5");
		}
		
		// INCOMPLETE TRITONE LOGIC
		
	}//end tritone
	
	
	private static void sharpFiveFlatSix() {
		
		// INCOMPLETE #5/b6 LOGIC
		
		// If 5 = NO and 3 = YES
		if((scale[7] == 0) && (scale[4] == 1)) {
			noteDegreeNames.add("#5");
		}
		else {
			noteDegreeNames.add("b6");
		}
		
		// INCOMPLETE #5/b6 LOGIC
		
	}//end sharpFiveFlatSix
	
}//end class ScaleDegreeNaming
