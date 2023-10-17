package reader_writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class rw06 {
	//메소드로 구현
	//이 메소드는 Reader객체에서 읽은 문자들을 Writer 객체에 출력한다.
	static void copyText(Reader reader, Writer writer) throws IOException {
		while (true) {
			int ch = reader.read();
			if (ch < 0)
				break;
			writer.write(ch);
		}
		reader.close();
		writer.close();
	}

	public static void main(String[] args) throws IOException {
		var url = new URL("https://www.skhu.ac.kr");
		var connection = (HttpURLConnection) url.openConnection();
		Reader reader = new InputStreamReader(connection.getInputStream());
		Writer writer = new FileWriter("home.html");
		copyText(reader, writer);
	}
}
