package Exercise;
/*
10개의 쓰레드를 만들어 실행한다.
각 쓰레드는 자신의 이름을 화면에 출력해야 한다. (예: "홍길동")
따라서 화면에 10줄이 출력되어야 한다.
Executir Service 방법으로 쓰레드를 구현해야 한다.
*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Runnabletest implements Runnable{
	String name;

	public Runnabletest(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.printf("%s \n", name);
	}
}



public class Exam2 {

		public static void main(String[] args) throws InterruptedException{

			ExecutorService service = Executors.newFixedThreadPool(10);
			//쓰레드를 최대 10개 생성한다.

			for(int i=0 ;i<10;i++) {
				service.submit(new Runnabletest("Jeon Hyeon O"));
			}
			//처리해야할 작업을 제출한다.

			service.shutdown();
			//제출된 작업을 다 실행한 후 쓰레드들이 자동 종료된다.

			service.awaitTermination(60,TimeUnit.SECONDS);
			//제출된 작업이 끝날 때까지 메인 쓰레드가 대기하게 된다.
			//최대 60초 대기했는데도 작업이 다 끝나지 않았다면 intertuptedExecption이 throw된다.
			System.out.println("완료");
		}
}
