package sports.score.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

public class WriteFile implements Writer {

	
	private BufferedWriter writer;
	
	public WriteFile (String outPath){
		try {
			writer = new BufferedWriter( new FileWriter( new File(outPath) ));
		} catch (IOException e) {
			// TODO add logging logic
			e.printStackTrace();
		}
	}
	
	@Override
	public void write(String str) {
		try {
			this.writer.write(str);
			this.writer.flush();
		} catch (IOException e) {
			// TODO Add logging logic
			e.printStackTrace();
		}
	}

}
