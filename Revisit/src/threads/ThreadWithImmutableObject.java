package threads;

public class ThreadWithImmutableObject {
	public static void main(String[] args) throws InterruptedException {
		ThreadSafeCounter counter = new ThreadSafeCounter();
		Runnable runnableFirst = () -> {
			for (int index = 0; index < 1000; index++) {
				counter.increement();
			}
		};
		Runnable runnableSecond = () -> {
			for (int index = 0; index < 1000; index++) {
				counter.increement();
			}
		};
		Thread threadFirst = new Thread(runnableFirst);
		Thread threadSecond = new Thread(runnableSecond);
		threadFirst.start();
		threadSecond.start();

		threadFirst.join();
		threadSecond.join();

		System.out.println(counter.count);
	}
}
