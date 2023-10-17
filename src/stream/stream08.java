package stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class stream08 {
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
		var url = new URL("https://www.skhu.ac.kr/sites/skhu/images/sub/ideo_3.jpg");

		var connection = (HttpURLConnection) url.openConnection();
		InputStream in = connection.getInputStream();
		FileOutputStream out = new FileOutputStream("ideo_3.jpg");
		copyStream(in, out);

		FileInputStream in2 = new FileInputStream("ideo_3.jpg");
		// 바로 위에서 다운로드한 "ideo_3.jpg" 파일을 읽기 위한 FileInputStream 객체 생성
		FileOutputStream out2 = new FileOutputStream("ideo_3_copy.jpg");
		// "ideo_3_copy.jpg" 파일을 생성하기 위한 FileOutputStream 객체 생성
		copyStream(in2, out2);
		//파일 복사
	}
}
