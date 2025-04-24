package threads;

public class ThreadSecond extends Thread {
	public void run() {
		for (int index = 0; index < 100; index++) {
			System.out.println("ThreadSecond");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
