package socket;

import java.net.Socket;

//특정 포트에 연결수신대기 중인 프로그램이 있는 확인하는 프로그램
public class PortScanner1 {
	public static void main(String[] args) {

		String host = "www.skhu.ac.kr";
		int portFrom = 1, portTo = 10000;

		for (int port = portFrom; port <= portTo; ++port) {
			System.out.printf("%s %d ", host, port);
			try (Socket socket = new Socket(host, port)) {
				System.out.println("연결 성공");
			} catch (Exception e) {
				System.out.println(e.getClass().getName() + " " + e.getMessage());
			}
		}
	}
}