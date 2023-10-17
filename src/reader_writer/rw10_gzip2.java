package reader_writer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.GZIPInputStream;

public class rw10_gzip2 {
	public static void main(String[] args) throws IOException {
		Reader reader = new InputStreamReader(
				new GZIPInputStream(new BufferedInputStream(new FileInputStream("out2.txt.gz"))));
		while (true) {
			int ch = reader.read();
			if (ch < 0)
				break;
			System.out.print((char) ch);
		}
		reader.close();
	}
}
