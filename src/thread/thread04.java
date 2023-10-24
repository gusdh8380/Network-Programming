package thread;

class SumTaskB implements Runnable {
	int from, to;

	public SumTaskB(int from, int to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public long run() {
		long sum = 0;
		for (int i = from; i <= to; ++i)
			sum += i;
		return sum;
	}
}

public class thread04 {
	public static void main(String[] args) {
		int from = 1, to = 5000000;
		SumTaskB sumTask = new SumTaskB(from, to);
		Thread thread = new Thread(sumTask);
		long result = thread.start();
		System.out.print(from + " 부터 " + to + " 까지 합계는 ");
		System.out.print(result);
	}
}
//컴파일 에러가 나는 코드!
// Runnable 인터페이스를 implement하여 구현하는 run 메소드의 리턴타입은 void이어야 함!
// 왜냐하면, override할 떄 동일한 형태로 구현해야하기 때문
//return 문으로 값을 리턴하는 것은 하나의 실행 흐름(thread)에서만 가능하다
//서로 다른 실행들이 값을 주고 받을 때, return 문을 사용할 수 없다