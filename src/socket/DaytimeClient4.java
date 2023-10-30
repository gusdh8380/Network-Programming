package socket;

import java.io.ObjectInputStream;
import java.net.Socket;

public class DaytimeClient4 {
	public static void main(String[] args) throws Exception {
		final String HOST = "localhost";
		try (Socket socket = new Socket(HOST, 13)) {
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			Message msg = (Message) in.readObject();
			System.out.printf("%s %s\n", msg.getValue(), msg.getDate());
		}
	}
}