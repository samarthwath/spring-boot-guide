package practice;

public class TestMyLambda {

	public static void main(String[] args) {
		MyLambda myLambda = (String name) -> {
			System.out.println("Hello " + name);
		};
		myLambda.sayHello("Samarth Wath");
	}
}
