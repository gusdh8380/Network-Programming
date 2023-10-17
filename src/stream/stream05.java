package stream;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class stream05 {
	public static void main(String[] args) throws IOException {

		var url = new URL("https://www.skhu.ac.kr");	//URL객체 생성

		var connection = (HttpURLConnection) url.openConnection();		//URL에 연

		InputStream in = connection.getInputStream();// URL 웹 페이지의 내용을 가져오기 위한 InputStream객체 생
		while (true) {
			int b = in.read();
			if (b < 0)
				break;
			System.out.print((char) b);
		}
		in.close();
	}
}