package threads;

public class TestThreadRunnable {
	public static void main(String[] args) {
		ThreadRunnableFirst threadRunnableFirst = new ThreadRunnableFirst();
		ThreadRunnableSecond threadRunnableSecond = new ThreadRunnableSecond();

		Thread threadFirst = new Thread(threadRunnableFirst);
		Thread threadSecond = new Thread(threadRunnableSecond);

		threadFirst.start();
		threadSecond.start();
	}
}
