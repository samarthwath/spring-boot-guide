package com.revise.designpatterns.structural.adapter;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("Inside MainClass ");

        //AppleCharger appleCharger = new ChargerXYZ();

        AndroidCharger androidCharger = new DKCharger();

        AppleCharger appleCharger = new AdapterCharger(androidCharger);


        Iphone13 iphone13 = new Iphone13(appleCharger);
        iphone13.chargeIphone();


    }

}
