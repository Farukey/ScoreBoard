package sports.score.io;

// provides an interface to communicate with underlying input source
public interface Reader {

	// interface method for reading line from a source
	public String readLine();
}
