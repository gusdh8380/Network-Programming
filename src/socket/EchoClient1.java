package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Random;

public class EchoClient1 {
	static Random random = new Random();
	static String[] a = { "one", "two", "three", "four" };

	static class EchoTask implements Runnable {
		@Override
		public void run() {
			// 서버에 어떤 문자열을 전송하고, 그 응답으로 서버가 전송한 문자열을 받아서 출력한다
			final String HOST = "localhost";
			final int PORT = 9090;

			try (Socket socket = new Socket(HOST, PORT)) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
				for (int i = 0; i < random.nextInt(10) + 1; i++) {
					String s1 = a[random.nextInt(a.length)];
					String s2 = a[random.nextInt(a.length)];
					String s3 = a[random.nextInt(a.length)];
					writer.write(s1);
					writer.write(s2);
					writer.write(s3);
					writer.write("\r\n");
					writer.flush();
					String s4 = reader.readLine();
					System.out.printf("%s, %s\n", s1, s4);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// EchoTask 클래스의 run 메소드를 실행하는 실행 흐름 200 개를 만들어서 거의 동시에 실행한다.
	public static void main(String[] args) {
		for (int i = 0; i < 200; ++i)
			new Thread(new EchoTask()).start();
	}
}