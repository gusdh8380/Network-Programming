package socket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeServer1 {
	public static void main(String[] args) {
		final int PORT = 13;    //포트 13번에서 클라이언트 연결을 기다릴 서버 소켓 객체 생성
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {

			while (true) {//아래 작업 반복

				try (Socket socket = serverSocket.accept()) {  //클라이언트 연결 기다리기

					Writer out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
					Date now = new Date();
					out.write(now.toString());
					out.write("\r\n");
					out.flush();  //전송되지 않고 버퍼에 남아있는 내욜을 마저 전송

				} catch (IOException ex) {  //클라이언트 연결 끊기, 위 코드 실행 중 에러시 실행
				}
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}