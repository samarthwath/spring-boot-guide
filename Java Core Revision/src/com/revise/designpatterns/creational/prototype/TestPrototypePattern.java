package com.revise.designpatterns.creational.prototype;

public class TestPrototypePattern {
    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {
        NetworkConnection networkConnection = new NetworkConnection();
        networkConnection.setIp("127.0.0.1");
        networkConnection.loadData();

        System.out.println("Log data from networkConnection: ");
        System.out.println(networkConnection);
        System.out.println("networkConnection hashCode: " + networkConnection.hashCode());

        NetworkConnection networkConnection1 = (NetworkConnection) networkConnection.clone();
        System.out.println("Log data from networkConnection1: ");
        System.out.println(networkConnection1);
        System.out.println("networkConnection1 hashCode: " + networkConnection1.hashCode());
    }

}
