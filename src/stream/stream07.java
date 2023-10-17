package stream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class stream07 {
	//InputStream 에서 읽은 내용을 OutputStream에 출력하는 메소드 copyStream
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
	}
	// out 객체의 타입은 FileOutputStream 이다.
	// 그런데 copyStream 메소드의 둘째 파라미터의 타입은 OutputStream 이다.
	// 타입이 일치하지 않는다. 그렇지만 에러가 아니다.
	// FileOutputStream 타입의 객체를 OutputStream 타입의 파라미터 변수에 대입할 수 있기 때문이다.
	// FileOutputStream 클래스는 OutputStream 클래스의 자식 클래스이다.
	// 예를 들어 남자 클래스 여자 클래스는 둘 다 사람 클래스의 자식 클래스이다.
	// 그래서 자식 타입인 남자 객체를, 부모 타입인 사람 변수에 대입할 수 있다.
	// 마찬가지로 자식 타입인 FileOutputStream 객체를 부모 타입인 OutputStream 변수에 대입할 수 있다.
}
