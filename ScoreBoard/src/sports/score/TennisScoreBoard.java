package sports.score;


import sports.score.io.Reader;
import sports.score.io.Writer;

public class TennisScoreBoard implements ScoreBoard {


	// Can be injected in constructor
	private sports.score.io.Reader reader = null;
	private sports.score.io.Writer writer = null;

	
	
	private enum Service{A, B};
	private String [] gameScore = {"0", "15", "30", "40", "A"};
	
	// A has the service at the beginning
	private Service currentService = Service.A;
	
	// Constructor
	public TennisScoreBoard( Reader reader, Writer writer){
		
		this.reader = reader; // Dependency Injection
		this.writer = writer; // Dependency Injection
	}
	
	public void publishScoreBoard(){
		
		
		// Scoring information
		int prevSetA = 0;
		int prevSetB = 0;
		int setWonByA = 0;
		int setWonByB = 0;
		
		int n,m, pn, pm = 0; // set score card tally
		String n1,m1 = "";// game score 
		

				// create a new output file deleting any old ones if they existed
				//scoreBoard.outFile.createNewFile();
				
				String inputLine = null; // This variable holds each line as the input file reads line after line
				StringBuilder strBuilder = new StringBuilder(); //String builder to store the output flushing it to the file
				
				
				int countA = 0, countB = 0; // variables for counting frequency in a line
				// Start reading from the input file and writing out on the output file
				while ( (inputLine = reader.readLine()) != null) {
					//System.out.println(aLine);
					// current Game score
					String gameA = "", gameB = "";
					
					if(inputLine.length() <= 0) {
						setWonByA = 0;
						setWonByB = 0;
					} else{
						for( char c : inputLine.toCharArray() ){ // counting the frequency of A and B
							if ( c == 'A')
								countA++;
							else countB++;
							
							
							
							// When either A or B gets to 24 and their opponent is two game behind
							// that means they won the set
							// add the score to previous set and reset the score
							if( (countA >= 24 && (countA-countB)>=8) || (countB >= 24) && (countB-countA) >= 8){
								prevSetA = countA/4;
								prevSetB = countB/4;
								
								countA = 0;
								countB = 0;
							}
							
						}
						
						if ( ((countA >=4 && countA <= 6) || (countB >= 4 && countB <=6 )) && Math.abs(countA-countB) < 2 ){
							if(countA > countB) {
								gameA = "A";
								gameB = "40";
							} else {
								gameA = "40";
								gameB = "A";
							}
						} 
						else {
							setWonByA = (int)Math.floor(countA/4);
							setWonByB = (int)Math.floor(countB/4);
							
							gameA = getGameScore(countA % 4);
							gameB = getGameScore(countB % 4);
						
						}
					}
					if( currentService == Service.A ){
						pn = prevSetA;
						n = setWonByA;
						n1 = gameA;
						
						pm = prevSetB;
						m  = setWonByB;
						m1 = gameB;
					} else {
						pn = prevSetB;
						n = setWonByB;
						n1 = gameB;
						
						pm = prevSetA;
						m = setWonByA;
						m1 = gameA;
					}
					
					if( prevSetA > 0 || prevSetB > 0 ){
						strBuilder.append(pn).append('-').append(pm); //display sets
						strBuilder.append(' ');
					}
					strBuilder.append(n).append('-').append(m); //display sets
					strBuilder.append(' ');
					
					if ( n1.length() > 0 )
						strBuilder.append(n1).append('-').append(m1);
					
					strBuilder.append(System.lineSeparator());
					
					//Resetting variable for next iteration
					countA = countB = 0;
				}
				writer.write(strBuilder.toString());
				

	}


	

	
	private String getGameScore(int i){
		if( i >= 0 && i <= 3 )
			return gameScore[i];
		return gameScore[4];
	}
	
	private void changeService(){
		this.currentService = this.currentService == Service.A ?
														Service.B :
														Service.A ;
	}
}
