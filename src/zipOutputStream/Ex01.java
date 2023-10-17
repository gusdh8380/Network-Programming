package zipOutputStream;

//웹에서 다운로드하는 코드
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Ex01 {
	static void download(String url, String path) throws IOException {
		var urlObj = new URL(url);
		var connection = (HttpURLConnection) urlObj.openConnection();
		InputStream in = new BufferedInputStream(connection.getInputStream());
		OutputStream out = new BufferedOutputStream(new FileOutputStream(path));
		while (true) {
			int b = in.read();
			if (b < 0)
				break;
			out.write(b);
		}
		in.close();
		out.close();
		connection.disconnect(); //서버 연결 끊기
	}

	public static void main(String[] args) throws IOException {
		File directory = new File("zip_test");

		if (directory.exists() == false)
			directory.mkdir(); //디렉토리가 없다면 새로 만들기

		download("https://www.naver.com", "zip_test/naver.html");
		download("https://news.naver.com", "zip_test/naver_news.html");
		download("https://map.naver.com", "zip_test/naver_map.html");
	}
}
/*
 * <finally 블럭에서 자원 반납 구현> 변수1 = null;
 *
 * 변수2 = null;
 *
 * try {
 *
 * 변수1 = new 자원객체1();
 *
 * 변수2 = new 자원객체2();
 *
 * 변수1.작업();
 *
 * 변수2.작업();
 *
 * ...
 *
 * } finally {
 *
 * if (변수1 != null) 변수1.close();
 *
 * if (변수2 != null) 변수2.close();
 *
 * }
 *
 * <try with resource 문법> try ( 변수1 = new 자원객체1();
 *
 * 변수2 = new 자원객체2(); ) {
 *
 * ...
 *
 * try ( 변수3 = new 자원객체3(); ) {
 *
 * ...
 *
 * }
 *
 * }
 *
 */
