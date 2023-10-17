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

public class Ex04 {
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
		//try with resource 문법 적용
		try (var out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream("test.zip")));) {
			for (String filePath : filePathList) {
				out.putNextEntry(new ZipEntry(filePath));
				try (var in = new BufferedInputStream(new FileInputStream(filePath));) {
					copyStream(in, out);
				}
			}
		}
	}
}