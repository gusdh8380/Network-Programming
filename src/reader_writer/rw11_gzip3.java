package reader_writer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

public class rw11_gzip3 {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new GZIPInputStream(new FileInputStream("out2.txt.gz"))));
		//gzip 파일 읽기 - 한 줄씩
		while (true) {
			String s = reader.readLine();
			//readLine 메소드가 Reader 클래스에 없고, BufferedReader 클래스에만 있다.
			//BufferedReader 객체를 만들어야 하고 BufferedReader 타입의 변수로 readLine 메소드를 호출
			if (s == null)
				break;
			System.out.println(s);
		}
		reader.close();
	}
}