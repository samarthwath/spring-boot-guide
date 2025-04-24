package designpatterns;

public class TestLazySingletonPattern {
	public static void main(String[] args) {
		Samosa samosaFirst = Samosa.getSamosaObject();
		Samosa samosaSecond = Samosa.getSamosaObject();
		System.out.println("Logging hashCodes of Samosa object: ");
		System.out.println(samosaFirst.hashCode());
		System.out.println(samosaSecond.hashCode());
	}
}
