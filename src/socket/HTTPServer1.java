package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class HTTPServer1 {
	static final int PORT = 80;


	static final String HEADER_TEMPLATE =
			"HTTP/1.0 200 OK\r\n" +
			"Server: HTTPServer1\r\n" +
			"Content-length: %d\r\n" +
			"Content-type: text/html; "+
			"charset=UTF-8\r\n\r\n";


	static final String BODY_TEMPLATE =
			"<!DOCTYPE html>\n" +
			"<html>\n<body>\n" +
			" <h2>안녕하세요 %d</h2>\n"+
			" <p>현재시각: %tT</p>\n" +
			"</body>\n</html>\n";

	public static void main(String[] args) {
		System.out.printf("start on port %d\n", PORT);
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			int count = 0;

			while (true) {
				try (Socket socket = serverSocket.accept()) {
					System.out.printf("--- request %s\n", socket.getRemoteSocketAddress());//연결된 상대편 주소 가져오는 코드
					Writer writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					while (true) {
						String s = reader.readLine();//한 줄 씩 읽
						if (s.isEmpty())
							break;//헤더가 끝 : 헤더와 바디 사이에 빈줄이 있기 때문
						System.out.println(s);
					}
					System.out.println("--- reponse\n");
					String body = String.format(BODY_TEMPLATE, ++count, new Date());
					String header = String.format(HEADER_TEMPLATE, body.getBytes("UTF-8").length);
					writer.write(header);
					writer.write(body);
					writer.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}