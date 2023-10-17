package reader_writer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class rw01 {
	public static void main(String[] args) throws IOException {
		Writer writer = new FileWriter("out.txt");
		writer.write("안녕하세요\n");
		writer.close();
		Reader reader = new FileReader("out.txt");
		char[] buffer = new char[100];
		while (true) {
			int count = reader.read(buffer);
			if (count < 0)
				break;
			for (int i = 0; i < count; ++i)
				System.out.print(buffer[i]);
		}
		reader.close();
	}//Inputstream/outputStream 과 구현이 유사함, 읽고 쓰는 데이터가 byte가 아닌 char이라는 점
}
