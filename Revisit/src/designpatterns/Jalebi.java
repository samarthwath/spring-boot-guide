package designpatterns;

public class Jalebi {
	private static Jalebi jalebi = new Jalebi();

	private Jalebi() {

	}

	public static Jalebi getJalebiObject() {
		return jalebi;
	}
}
