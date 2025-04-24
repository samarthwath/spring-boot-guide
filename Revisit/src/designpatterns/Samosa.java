package designpatterns;

public class Samosa {
	private static Samosa samosa;

	private Samosa() {

	}

	/**
	 * In order to achieve thread safe behavior for singleton object we can make use
	 * of the method synchornization or block synchronization approach.
	 * 
	 * @return
	 */
	public synchronized static Samosa getSamosaObject() {
		if (samosa == null) {
			samosa = new Samosa();
		}
		return samosa;
	}
}
