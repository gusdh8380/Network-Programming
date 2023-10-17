package thread;

public class thread01 {
	static class TestThread extends Thread { //Thread 클래스 상속(1)

		String message;

		public TestThread(String message) {
			this.message = message;
		}

		@Override
		public void run() {
			for (int count = 0; count < 4; ++count)
				System.out.printf("%s %d\n", message, count);
		}
	}

	public static void main(String[] args) {
		Thread threadA = new TestThread("threadA"); //Thread 객체 생성(2)
		threadA.start();

		Thread threadB = new TestThread("threadB");
		threadB.start();

		new TestThread("threadC").start(); //Thread 흐름 생성
	}
}