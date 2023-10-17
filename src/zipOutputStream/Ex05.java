package zipOutputStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Ex05 {
	static void copyStream(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[4096];
		while (true) {
			int count = in.read(buffer);
			if (count < 0)
				break;
			out.write(buffer, 0, count);
		}
	}
	// 파라미터로 주어진 path 경로명으로 디렉토리를 생성한다.
	// 예) zip_test2/zip_test
	// 위의 예에서 zip_test2 디렉토리가 없다면 만들고,
	// 그 다음 zip_test2/zip_test 디렉토리가 없다면 만든다.
	// 이미 그 디렉토리가 있다면 다시 만들지 않고 그냥 리턴한다.
	static void createDirectory(String path) throws IOException {
		Files.createDirectories(Paths.get(path));
	}

	public static void main(String[] args) throws IOException {
		try (var in = new ZipInputStream(new BufferedInputStream(new FileInputStream("test.zip")));) {
			while (true) {
				ZipEntry entry = in.getNextEntry();
				if (entry == null)
					break;
				String outputName = "zip_test2/" + entry.getName();//다음 항목을 저장할 경로명을 outputName에 대입한다.
				if (entry.isDirectory()) {				//그 다음항목이 디렉토리이
					createDirectory(outputName);		//그 디렉토리를 생성
				} else {								//다음 항복이 파일이면
					String directory = new File(outputName).getParent();	//파일 경로명에서 디렉토리부분만 추출
					createDirectory(directory);								//디렉토리를 생성

					try (var out = new BufferedOutputStream(new FileOutputStream(outputName));) {
						copyStream(in, out);
					}
				}
			}
		}
	}
}