package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class HTTPServer2 {
	static final int PORT = 80;
	static final String HEADER_TEMPLATE = "HTTP/1.0 200 OK\r\n" + "Server: HTTPServer2\r\n" + "Content-length: %d\r\n"
			+ "Content-type: text/html; charset=UTF-8\r\n\r\n";
	static final String BODY_TEMPLATE = "<!DOCTYPE html>\n" + "<html>\n<body>\n" + " <h2>안녕하세요 %d</h2>\n"
			+ " <p>현재시각: %tT</p>\n" + "</body>\n</html>\n";

	public static void main(String[] args) {
		System.out.printf("start on port %d\n", PORT);
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			int count = 0;
			while (true) {
				try (Socket socket = serverSocket.accept()) {
					System.out.printf("--- request %s\n", socket.getRemoteSocketAddress());
					//write를 사용하지 않
					//OutputStream에 byte[] 배열을 그냥 출력
					OutputStream out = socket.getOutputStream();

					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					while (true) {
						String s = reader.readLine();
						if (s.isEmpty())
							break;
						System.out.println(s);
					}
					System.out.println("--- reponse\n");
					//getBytes 메소드는, 문자열을 byte[] 배열 형태로 변환해서 리턴 -> OutputStream을 사용해도 되는 이유
					byte[] body = String.format(BODY_TEMPLATE, ++count, new Date()).getBytes("UTF-8");
					byte[] header = String.format(HEADER_TEMPLATE, body.length).getBytes("US-ASCII");
					out.write(header);
					out.write(body);
					out.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}