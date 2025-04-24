package designpatterns;

public class TestEagerSingletonDesignPattern {
	public static void main(String[] args) {
		System.out.println(Jalebi.getJalebiObject().hashCode());
		System.out.println(Jalebi.getJalebiObject().hashCode());
	}
}
