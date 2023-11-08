package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//클라이언트와 서버가 데이터를 주고 받는 코드 구현
public class EchoServer1 {
	public static void main(String[] args) {
		final int PORT = 9090;
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			// 쓰레드 최대 200개
			ExecutorService executor = Executors.newFixedThreadPool(200);
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					executor.submit(new EchoTask(socket));
				} catch (IOException ex) {
				}
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	static class EchoTask implements Runnable {
		Socket socket;

		public EchoTask(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (Socket autoClose = socket) { // socket 객체의 close 메소드 호출이 보장
				BufferedReader reader = new BufferedReader(new InputStreamReader(autoClose.getInputStream(), "UTF-8"));
				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(autoClose.getOutputStream(), "UTF-8"));
				while (true) {
					String s = reader.readLine();
					writer.write(s.toUpperCase());
					writer.write("\r\n");
					writer.flush();
				}
			} catch (Exception ex) {
			}
		}
	}
}