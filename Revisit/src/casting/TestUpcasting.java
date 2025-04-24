package casting;

public class TestUpcasting {
	public static void main(String[] args) {
		UpParent upParent = new UpChild();
		System.out.println(upParent.hashCode());
		upParent.show();
		System.out.println(upParent.x);
	}
}
