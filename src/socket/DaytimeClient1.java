package socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class DaytimeClient1 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		final String HOST = "localhost";
		final int PORT = 13;
		try (Socket socket = new Socket(HOST, PORT)) {
			InputStream in = socket.getInputStream();
			StringBuilder result = new StringBuilder();
			while (true) {
				int c = in.read();
				if (c == -1)
					break;
				result.append((char) c);
			}
			System.out.println(result.toString());
		}
	}
}