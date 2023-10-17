package reader_writer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class rw07 {

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
		//문자 인코딩 변환
		//InputStreamReader: InputStream 객체에서 읽어온 데이터를 문자로 변환해서 리턴하는 Reader
		//OutputStreamWriter: 출력할 문자를 OutputStream 객체에 출력하기 위한 Writer

		Reader reader = new InputStreamReader(new FileInputStream("home.html"), "UTF-8");
		//위 코드와 같은 코드 : Reader reader = new FileReader("home.html");
		//주석의 코드는 유니코드와 같은 표준 문자 인코딩 파일을 읽을 때만 사용!!
		Writer writer = new OutputStreamWriter(new FileOutputStream("home2.html"), "euc-kr");
		copyText(reader, writer);
		//UTF-8 인코딩한 파일을 읽어서 euc-kr 인코딩으로 다른파일로 저장
	}
}
