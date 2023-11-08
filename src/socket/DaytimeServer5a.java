package socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeServer5a {
	public static void main(String[] args) {
		final int PORT = 13, BACKLOG = 200;
		try (ServerSocket serverSocket = new ServerSocket(PORT, BACKLOG)) {
			while (true) {
				//버그 코드
				try (Socket socket = serverSocket.accept()) {
					Thread thread = new Thread(new DaytimeTask(socket));
					thread.start();
				} catch (IOException ex) {
				}
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	static class DaytimeTask implements Runnable {
		Socket socket;

		public DaytimeTask(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(100);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				Message msg = new Message("안녕하세요", new Date());
				out.writeObject(msg);
				out.flush();
			} catch (Exception ex) {
			}
		}
	}
}