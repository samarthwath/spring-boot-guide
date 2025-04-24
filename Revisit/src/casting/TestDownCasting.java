package casting;

public class TestDownCasting {
	public static void main(String[] args) {
		UpParent upParent = new UpChild();
		UpChild upChild = (UpChild) upParent;
		System.out.println(upChild.x);
	}
}
