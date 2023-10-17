package stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class stream03 {
	public static void main(String[] args) throws IOException {

		final String filePath = "out.txt";

		FileOutputStream out = new FileOutputStream(filePath); //파일 출력 스트림 객체를 생성
		byte[] a = new byte[] { 'h', 'e', 'l', 'l', 'o', 32, 119, 111, 114, 108, 100 };
		for (int i = 0; i < a.length; ++i)
			out.write(a[i]);

		out.close();


		FileInputStream in = new FileInputStream(filePath);
		while (true) {
			int b = in.read();
			if (b < 0)
				break;
			System.out.printf("%d", b);	//수정된 부분
		}
		in.close();
	}
}