package stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class stream02 {
	public static void main(String[] args) throws IOException {

		final String filePath = "out.txt";

		FileOutputStream out = new FileOutputStream(filePath); //파일 출력 스트림 객체를 생성
		byte[] a = new byte[] { 'h', 'e', 'l', 'l', 'o', 32, 119, 111, 114, 108, 100 };
		for (int i = 0; i < a.length; ++i)
			out.write(a[i]);
		/*
		stream01 과 구현이 다른 부분, 배열 a 전체를 한 번에 출력해도 되지만,
		반복문을 통해 한 바이트씩 출력해도 문제없고 차이가 거의 없다.
		 */

		out.close();


		FileInputStream in = new FileInputStream(filePath);
		while (true) {
			int b = in.read();	//한 바이트를 읽어서 리턴한다.
			if (b < 0)
				break;	// in.read() 메소드에서 -1 리턴 값이 나오면 파일의
			System.out.print((char) b);	//char타입으로 변환하여 문자를 출력
		}
		in.close();
	}
}