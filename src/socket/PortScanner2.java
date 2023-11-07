package socket;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PortScanner2 {
	static class PortTask implements Runnable {
		String host;
		int port;

		public PortTask(String host, int port) {
			this.host = host;
			this.port = port;
		}

		@Override
		public void run() {
			try (Socket socket = new Socket(host, port)) {
				System.out.printf("%d 연결 성공\n", port);
			} catch (Exception e) { // 연결할 수 없다
			}
		}
	}

	public static void main(String[] args) {
		String host = "www.skhu.ac.kr";
		int portFrom = 1, portTo = 10000;
		ExecutorService executor = Executors.newFixedThreadPool(1000);
		for (int port = portFrom; port <= portTo; ++port) {
			executor.submit(new PortTask(host, port));
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
		}
		System.out.println("DONE");
	}
}
