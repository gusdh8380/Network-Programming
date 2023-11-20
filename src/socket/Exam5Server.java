package socket;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exam5Server {
	public static void main(String[] args) {
		final int PORT = 8080;
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			// 쓰레드 최대 200개
			ExecutorService executor = Executors.newFixedThreadPool(200);
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					executor.submit(new EchoTask(socket));
				} catch (IOException ex) {
				}
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	static class EchoTask implements Runnable {
		Socket socket;

		public EchoTask(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (Socket autoClose = socket) { // socket 객체의 close 메소드 호출이 보장
			    InputStream inputStream = socket.getInputStream();
		        byte[] bytes = new byte[1024];
		        int length = inputStream.read(bytes);
		        String message = new String(bytes, 0, length);

		        // 클라이언트에게 응답 보내기
		        OutputStream outputStream = socket.getOutputStream();
		        outputStream.write((message + " 오케이!").getBytes());
		        outputStream.flush();
			} catch (Exception ex) {
			}
		}
	}
}