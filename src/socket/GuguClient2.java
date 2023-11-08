package socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
// double타입으로 주고 받기
public class GuguClient2 {
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
					double a = random.nextInt(9) + 1;
					double  b= random.nextInt(9) + 1;
					out.writeDouble(a);
					out.writeDouble(b);
					out.flush();
					double result = in.readDouble();
					System.out.printf("%f x %f = %f\n", a, b, result);
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