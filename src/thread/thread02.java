package thread;

public class thread02 {
	static class TestRunnable implements Runnable {
		String message;

		public TestRunnable(String message) {
			this.message = message;
		}

		@Override
		public void run() {
			for (int count = 0; count < 4; ++count)
				System.out.printf("%s %d\n", message, count);
		}
	}

	public static void main(String[] args) {
		Thread threadA = new Thread(new TestRunnable("threadA"));
		threadA.start();
		Thread threadB = new Thread(new TestRunnable("threadB"));
		threadB.start();
		new Thread(new TestRunnable("threadC")).start();
	}
}