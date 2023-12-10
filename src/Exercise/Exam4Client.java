package Exercise;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Exam4Client {
	static String a = "전현오";

	static class EchoTask implements Runnable {
		@Override
		public void run() {
			final String HOST = "localhost";
			final int PORT = 10014;
			try (Socket socket = new Socket(HOST, PORT)) {

				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
				String s1 = a;
				writer.write(s1);
				writer.write("\r\n");
				writer.flush();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
			new Thread(new EchoTask()).start();
	}
}
