package reader_writer;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.zip.GZIPOutputStream;
//gzip 출력하기
public class rw09_gzip1 {
	public static void main(String[] args) throws IOException {
		Writer writer = new OutputStreamWriter(
				new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream("out2.txt.gz"))));
		//write -> OutputStreamWriter(GZIPOutputsteram(BufferOutputStream(FileOutputStream())))


		for (int i = 0; i < 10000; ++i)
			writer.write("안녕하세요 여러분\n");
		writer.close();
	}
}
