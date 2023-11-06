package socket;

import java.io.ObjectInputStream;
import java.net.Socket;

public class DaytimeClient5 {
	static final String HOST = "localhost";
	static final int PORT = 13;

	static class GetMessageTask implements Runnable {
		@Override
		public void run() {
			try (Socket socket = new Socket(HOST, PORT)) {
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				Message msg = (Message) in.readObject();
				System.out.printf("%s %s\n", msg.getValue(), msg.getDate());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 200; ++i)
			new Thread(new GetMessageTask()).start();
	}
}