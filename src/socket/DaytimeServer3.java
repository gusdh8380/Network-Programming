package socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeServer3 {
	public static void main(String[] args) {
		final int PORT = 13;
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			while (true) {
				try (Socket socket = serverSocket.accept()) {
					// 객체를 전송하기 위해 ObjectOutputStream 객체를 사용해야한다
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
					Date now = new Date(); //객체를 전송한다
					out.writeObject(now);
					out.flush();
				} catch (IOException ex) {
				}
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}