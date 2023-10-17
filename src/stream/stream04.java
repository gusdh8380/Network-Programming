package stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class stream04 {
	public static void main(String[] args) throws IOException {
		final String filePath = "out.txt";
		FileOutputStream out = new FileOutputStream(filePath);
		byte[] a = new byte[] { 'h', 'e', 'l', 'l', 'o', 32, 119, 111, 114, 108, 100, '\n' };
		for (int i = 0; i < 200; ++i)
			out.write(a);
		out.close();
		FileInputStream in = new FileInputStream(filePath);
		byte[] buffer = new byte[100];

		while (true) {		//파일이 클수도 있으니, 100바이트씩 여러 반복해서 읽도록
			int count = in.read(buffer);	// 파일에 끝에서 -1 리턴
			if (count < 0)					//-1이면 끝
				break;
			for (int i = 0; i < count; ++i)
				System.out.print((char) buffer[i]);
		}
		in.close();
	}
}