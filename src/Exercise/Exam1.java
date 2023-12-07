package Exercise;

/*
 10개의 쓰레드를 만들어 실행한다.
각 쓰레드는 자신의 이름을 화면에 출력해야 한다. (예: "홍길동")
따라서 화면에 10줄이 출력되어야 한다.
implements Runnable 방법으로 쓰레드를 구현해야 한다.
 */
public class Exam1 {

	static class Runnabletest implements Runnable {
		String message;

		public Runnabletest(String message) {
			this.message = message;
		}

		@Override
		public void run() {
			System.out.printf("%s \n", message);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Thread a = new Thread(new Runnabletest("Jeon Hyeon O"));
			a.start();
		}
	}
}
