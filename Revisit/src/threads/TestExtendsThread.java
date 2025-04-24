package threads;

public class TestExtendsThread {
	public static void main(String[] args) {
		ThreadFirst threadFirst = new ThreadFirst();
		ThreadSecond threadSecond = new ThreadSecond();
		threadFirst.setPriority(Thread.MAX_PRIORITY);
		threadSecond.setPriority(Thread.MAX_PRIORITY);
		threadFirst.start();
		threadSecond.start();
	}
}
