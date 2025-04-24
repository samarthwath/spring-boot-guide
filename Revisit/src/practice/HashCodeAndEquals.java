package practice;

public class HashCodeAndEquals {
	private String name;
	private String code;

	public HashCodeAndEquals(String name, String code) {
		this.name = name;
		this.code = code;
	}

	@Override
	public String toString() {
		return "HashCodeAndEquals [name=" + name + ", code=" + code + "]";
	}

	@Override
	public int hashCode() {
		return name.hashCode() + code.hashCode();

	}

	@Override
	public boolean equals(Object object) {
		HashCodeAndEquals hashCodeAndEquals = (HashCodeAndEquals) object;
		return this.name.equals(hashCodeAndEquals.name) && this.code.equals(hashCodeAndEquals.code);
	}

	public static void main(String[] args) {
		HashCodeAndEquals hashCodeAndEqualsFirst = new HashCodeAndEquals("Samarth", "10696");
		HashCodeAndEquals hashCodeAndEqualsSecond = new HashCodeAndEquals("Pushpak", "10697");
		HashCodeAndEquals hashCodeAndEqualsThird = new HashCodeAndEquals("Samarth", "10696");
		System.out.println(
				"HashCodeAndEqualsFirst" + hashCodeAndEqualsFirst + " :: " + hashCodeAndEqualsFirst.hashCode());
		System.out.println(
				"HashCodeAndEqualsSecond" + hashCodeAndEqualsSecond + " :: " + hashCodeAndEqualsSecond.hashCode());
		System.out.println(
				"HashCodeAndEqualsThird" + hashCodeAndEqualsThird + " :: " + hashCodeAndEqualsThird.hashCode());
		System.out.println(hashCodeAndEqualsFirst.equals(hashCodeAndEqualsThird));
	}
}
