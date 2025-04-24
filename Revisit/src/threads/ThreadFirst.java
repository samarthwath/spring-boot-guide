package threads;

public class ThreadFirst extends Thread {
	public void run() {
		for (int index = 0; index < 100; index++) {
			System.out.println("ThreadFirst");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
