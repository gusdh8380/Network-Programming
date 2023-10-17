package stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class stream01 {
	public static void main(String[] args) throws IOException {

		final String filePath = "out.txt";

		FileOutputStream out = new FileOutputStream(filePath); //파일 출력 스트림 객체를 생성

		byte[] a = new byte[] { 'h', 'e', 'l', 'l', 'o', 32, 119, 111, 114, 108, 100 };
		out.write(a); //배열 a에 들어있는 byte들을 출력한다
		out.close();	//사용이 끝난 스트림은 반드시 닫아야한다.

		FileInputStream in = new FileInputStream(filePath);	// 파일 입력 스트림 객체를 생
		byte[] buffer = new byte[100];
		int count = in.read(buffer);		//입력 스트림으로 부터 byte데이터를 읽어서 buffer 배열에 채운다

		for (int i = 0; i < count; ++i)		//읽어온 데이터 각각을 char 타입으로 변환해서 문자를 출력
			System.out.print((char) buffer[i]);
		in.close();
	}
}
