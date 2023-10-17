package thread;

class SumTaskA implements Runnable {
	int from, to;

	public SumTaskA(int from, int to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public void run() {
		long result;
		long sum = 0;
		for (int i = from; i <= to; ++i)
			sum += i;
		result = sum;
		System.out.print(from + " 부터 " + to + " 까지 합계는 ");
		System.out.print(result);
	}
}

public class thread03 {
	public static void main(String[] args) {
		int from = 1, to = 5000000;
		SumTaskA sumTask = new SumTaskA(from, to);
		Thread thread = new Thread(sumTask);
		thread.start();
	}
}
// 쓰레드에서 계산 작업을 마치고, 그 결과를 출력하는 직관적이고 단순한 구현