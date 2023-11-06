package socket;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;

public class DaytimeClient3 {
	public static void main(String[] args) throws Exception {
		final String HOST = "localhost";
		final int PORT = 13;
		try (Socket socket = new Socket(HOST, PORT)) {
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			Date dt = (Date) in.readObject();
			System.out.println(dt);
		}
	}
}