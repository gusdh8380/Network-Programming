package zipOutputStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Ex03 {
	static void copyStream(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[4096];
		while (true) {
			int count = in.read(buffer);
			if (count < 0)
				break;
			out.write(buffer, 0, count);
		}
	}

	public static void main(String[] args) throws IOException {

		String[] filePathList = { "zip_test/naver.html", "zip_test/naver_news.html", "zip_test/naver_map.html" };

		var out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream("test.zip")));
		// 압축 파일을 생성하기 위한 zipoutputstream 객체를 생성

		for (String filePath : filePathList) {
			out.putNextEntry(new ZipEntry(filePath)); // 저장할 파일의 정보 먼저 기록

			var in = new BufferedInputStream(new FileInputStream(filePath)); //저장할 파일 내용 기록
			copyStream(in, out);
			in.close();
		}
		out.close();
	}
}
