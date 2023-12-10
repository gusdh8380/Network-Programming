package Exercise;

import java.io.ObjectInputStream;
import java.net.Socket;

// 객체타입을 주고 받는 서버과 클라 구현
public class Exam6Client {

	static class GuguTask implements Runnable {
		@Override
		public void run() {
			final String HOST = "localhost";
			final int PORT = 10015;
			try (Socket socket = new Socket(HOST, PORT)) {
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				DTO d = (DTO) in.readObject();
				System.out.printf("%s", d.value1);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; ++i) {
			new Thread(new GuguTask()).start();
		}
	}

}