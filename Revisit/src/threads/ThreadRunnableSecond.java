package threads;

public class ThreadRunnableSecond implements Runnable {
	@Override
	public void run() {
		for (int index = 0; index < 15; index++) {
			System.out.println("ThreadRunnableSecond");
		}
	}
}
