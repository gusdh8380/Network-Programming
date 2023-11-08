package socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class GuguClient1 {
	static Random random = new Random();

	static class GuguTask implements Runnable {
		@Override
		public void run() {
			final String HOST = "localhost";
			final int PORT = 9090;
			try (Socket socket = new Socket(HOST, PORT)) {
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				for (int i = 0; i < 10; ++i) {
					int a = random.nextInt(9) + 1;
					int b = random.nextInt(9) + 1;
					out.writeInt(a);
					out.writeInt(b);
					out.flush();
					int result = in.readInt();
					System.out.printf("%d x %d = %d\n", a, b, result);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; ++i)
			new Thread(new GuguTask()).start();
	}
}