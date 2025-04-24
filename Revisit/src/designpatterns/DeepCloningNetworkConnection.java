package designpatterns;

import java.util.ArrayList;
import java.util.List;

public class DeepCloningNetworkConnection {
	private String ip;
	private String impoatantData;
	private List<String> domains = new ArrayList();

	public String getIp() {
		return ip;
	}

	public List<String> getDomains() {
		return domains;
	}

	public void setDomains(List<String> domains) {
		this.domains = domains;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "NetworkConnection [ip=" + ip + ", impoatantData=" + impoatantData + ", domains=" + domains + "]";
	}

	public DeepCloningNetworkConnection() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeepCloningNetworkConnection(String ip, String impoatantData) {
		super();
		this.ip = ip;
		this.impoatantData = impoatantData;
	}

	public String getImpoatantData() {
		return impoatantData;
	}

	public void setImpoatantData(String impoatantData) {
		this.impoatantData = impoatantData;
	}

	public void loadImportantData() throws InterruptedException {
		domains.add("www.samarthwath11@gmail.com");
		domains.add("www.google.com");
		domains.add("www.yahoo.com");
		System.out.println("VeryImportantData: " + ip + " " + impoatantData);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		DeepCloningNetworkConnection deepCloningNetworkConnection = new DeepCloningNetworkConnection();
		deepCloningNetworkConnection.setIp(this.getIp());
		deepCloningNetworkConnection.setImpoatantData(this.getImpoatantData());
		System.out.println("Logging domains before cloning process: ");
		System.out.println(deepCloningNetworkConnection.getDomains());
		for (String domain : this.getDomains()) {
			deepCloningNetworkConnection.getDomains().add(domain);
		}
		// TODO Auto-generated method stub
		return deepCloningNetworkConnection;
	}

}
