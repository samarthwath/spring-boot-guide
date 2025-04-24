package designpatterns;

public class TestPrototypeDesignPattern {
	public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {
		NetworkConnection networkConnection = new NetworkConnection();
		networkConnection.setIp("192.168.4.4");
		networkConnection.setImpoatantData("Hello Guys!!!!!!!!!");
		networkConnection.loadImportantData();
		System.out.println("Logging networkConnction object: ");
		System.out.println(networkConnection);
		System.out.println("Logging hashCode of networkConnection: ");
		System.out.println(networkConnection.hashCode());

		NetworkConnection networkConnectionSecond = (NetworkConnection) networkConnection.clone();
		System.out.println("Logging networkConnectionSecond object: ");
		System.out.println(networkConnectionSecond);
		System.out.println("Logging hashCode of networkConnectionSecond: ");
		System.out.println(networkConnectionSecond.hashCode());

		networkConnection.getDomains().remove(0);
		System.out.println("Looging objects after removal: ");
		System.out.println(networkConnection);
		System.out.println(networkConnectionSecond);

	}
}
