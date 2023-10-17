package stream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class streram06 {
	public static void main(String[] args) throws IOException {

		var url = new URL("https://www.skhu.ac.kr/sites/skhu/images/sub/ideo_3.jpg");
		//학교 홈페이지 이미지 파일URL
		var connection = (HttpURLConnection) url.openConnection();
		//URL 연결, 웹 서버에 연결
		InputStream in = connection.getInputStream();
		//받아올 InputStream 객체 생
		FileOutputStream out = new FileOutputStream("ideo_3.jpg");
		//읽은 내용을 저장할 flieOutputStream 객체 생성

		while (true) {
			int b = in.read();
			if (b < 0)
				break;
			out.write(b);
		}
		in.close();
		out.close();
	}
}
