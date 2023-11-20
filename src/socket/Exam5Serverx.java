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

public class Exam5Serverx {
	public static void main(String[] args) {
		final int PORT = 8080;
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

				String s = reader.readLine();
				writer.write(s + "ok");
				writer.flush();

			} catch (Exception ex) {
			}
		}
	}
}