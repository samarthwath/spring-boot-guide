package practice;

public class InnerClassExample {
	public void show() {
		System.out.println("Inside show method of InnerClassExample.");
	}

	class Inner {
		public void config() {
			System.out.println("Inside config method of Inner class.");
		}
	}
}
