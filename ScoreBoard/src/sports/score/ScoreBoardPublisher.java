package sports.score;

import sports.score.io.ReadFile;
import sports.score.io.WriteFile;

public class ScoreBoardPublisher {

	private static String inputPath = "input.txt";		// hard coded to ensure program goes through even without p 
	private static String outputPath = "output.txt";	// for testing
	
	
	public static void main( String args[] ){
		
		// get filenames out of command line arguments
		getFileNames(args);
		
		ScoreBoard scoreBoard = new TennisScoreBoard( new ReadFile(inputPath), new WriteFile(outputPath) );
		
		scoreBoard.publishScoreBoard();
	}
	
	
	// Helper methods
	// get filenames out of command line and populate static variables with them
	private static void getFileNames(String[] args) {
		// Check to see if input filename is given if not use default input file
		int index = 0; // variable to access elements in args[].
		if (args.length > 0) {
			if( args[index] != null){
				inputPath = args[index];
			} else {
				System.out.println("Input file name is not given, using default input file");
			}
			// Check to see whether output filename is given if not use the default output file name.
			index++; // move on to next index to get output file name
			if( args[index] != null){ 
				outputPath = args[index];
			} else {
				System.out.println("Output file name is not given, using default output file");
			}
		} else {
			System.out.println("Input and Output filenames not supplied in command line arguments using default input/output files.");
		}
	}
	
}
