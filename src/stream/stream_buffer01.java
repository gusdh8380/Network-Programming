package stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class stream_buffer01 {
	static void copyStream(InputStream in, OutputStream out) throws IOException {
		while (true) {
			int b = in.read();
			if (b < 0)
				break;
			out.write(b);
		}
		in.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		InputStream in1 = new FileInputStream("xideo_3.jpg");
		OutputStream out1 = new FileOutputStream("ideo_3_copy.jpg");
		copyStream(in1, out1);

		InputStream in2 = new BufferedInputStream(new FileInputStream("xideo_3.jpg"));
		OutputStream out2 = new BufferedOutputStream(new FileOutputStream("ideo_3_copy.jpg"));
		copyStream(in2, out2);
	}
	/*FileInputStream in1 = new FileInputStream("data.txt");
	  BufferedInputStream in2 = new BufferedInputStream(in1);
	  를 한번에 구현한 것이 바로
	  InputStream in2 = new BufferedInputStream(new FileInputStream("data.txt"));
	 */

}
