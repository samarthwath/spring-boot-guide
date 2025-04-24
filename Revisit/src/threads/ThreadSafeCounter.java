package threads;

public class ThreadSafeCounter {
	int count;

	public synchronized void increement() {
		count++;
	}
}
