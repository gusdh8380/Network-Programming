package zipOutputStream;

//자원 반납 구현
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Ex02 {
	static void download(String url, String path) throws IOException {
		var urlObj = new URL(url);
		HttpURLConnection connection = null;
		InputStream in = null;
		OutputStream out = null;
		//반납해야하는 변수를 null로 초기

		try {
			//자원 할당
			connection = (HttpURLConnection) urlObj.openConnection();
			in = new BufferedInputStream(connection.getInputStream());
			out = new BufferedOutputStream(new FileOutputStream(path));
			while (true) {
				int b = in.read();
				if (b < 0)
					break;
				out.write(b);
			}
		} finally {
			//자원이 할당된 변수의 값은 null이 아님
			//아래코드로 자원 반납코드가 모든 상황에서 실행된다.
			if (in != null)
				in.close();
			if (out != null)
				out.close();
			if (connection != null)
				connection.disconnect();
		}
	}

	public static void main(String[] args) throws IOException {
		File directory = new File("zip_test");
		if (directory.exists() == false)
			directory.mkdir();
		download("https://www.naver.com", "zip_test/naver.html");
		download("https://news.naver.com", "zip_test/naver_news.html");
		download("https://map.naver.com", "zip_test/naver_map.html");
	}

}
