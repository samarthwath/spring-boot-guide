package practice;

public class ExceptionHierarchy {
	public static void main(String[] args) {
		try {
			int a = 50;
			System.out.println(a / 0);
		} catch (ArithmeticException arithmeticException) {
			arithmeticException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
