package socket;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PortScanner3 {
	static class PortTask implements Runnable {
		String host;
		int port, timeout; //timeout 변수 추가생성

		public PortTask(String host, int port, int timeout) {
			this.host = host;
			this.port = port;
			this.timeout = timeout;
		}

		@Override
		public void run() {
			try (Socket socket = new Socket()) {
				SocketAddress address = new InetSocketAddress(host, port);
				socket.connect(address, timeout);
				System.out.printf("%s %d 연결 성공\n", host, port);
			} catch (Exception e) { // 연결할 수 없다
			}
		}
	}

	public static void main(String[] args) {
		String host = "www.skhu.ac.kr";
		int timeout = 300; //300밀리초로 설정
		int portFrom = 1, portTo = 10000;

		ExecutorService executor = Executors.newFixedThreadPool(100);
		for (int port = portFrom; port <= portTo; ++port) {
			executor.submit(new PortTask(host, port, timeout));
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
		}
		System.out.println("DONE");
	}
}
