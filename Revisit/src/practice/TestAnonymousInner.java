package practice;

public class TestAnonymousInner {
	public static void main(String[] args) {
		int x = 5;
		AnonymousInner anonymousInner = new AnonymousInner() {
			public void show() {
				System.out.println(x);
				System.out.println("Inside the show method of AnonymousInner class.");
			}
		};
		anonymousInner.show();
	}
}
