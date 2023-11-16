package socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
// 객체타입을 주고 받는 서버과 클라 구현
public class GuguClient3 {
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
					int  b= random.nextInt(9) + 1;
					GuguDTO gugu = new GuguDTO(a,b);
					out.writeObject(gugu);
					out.flush();
					gugu = (GuguDTO)in.readObject();
					System.out.printf("%d x %d = %d\n", gugu.value1, gugu.value2, gugu.result);
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