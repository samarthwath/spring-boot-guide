package threads;

import java.util.HashSet;
import java.util.Set;

public class ThreadsUsingLambda {
	public static void main(String[] args) {
		Runnable runnableFirst = () -> {
			for (int index = 0; index < 15; index++) {
				System.out.println("RunnableFirst");
			}
		};
		Runnable runnableSecond = () -> {
			for (int index = 0; index < 15; index++) {
				System.out.println("RunnableSecond");
			}
		};
		Thread threadFirst = new Thread(runnableFirst);
		Thread threadSecond = new Thread(runnableSecond);
		threadFirst.start();
		threadSecond.start();
	}
}
