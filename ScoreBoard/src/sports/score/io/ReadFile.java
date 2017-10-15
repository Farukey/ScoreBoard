package sports.score.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile implements Reader {

	private BufferedReader reader = null;
	
	public ReadFile( String inPath ){
		try {
			reader = new BufferedReader( new FileReader(new File(inPath)) );
		} catch (FileNotFoundException e) {
			// logging can be added here using AOP
			e.printStackTrace();
		}
	}

	@Override
	public String readLine() {
		try {
			return this.reader.readLine();
		} catch (IOException e) {
			// TODO add logging logic
			e.printStackTrace();
		}
		// if I/O exception return null
		return null;
	}
	

}
