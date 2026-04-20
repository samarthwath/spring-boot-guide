package com.revise.designpatterns.structural.facade;

public class TestFacade {
    public static void main(String[] args) {

        HomeTheatre homeTheatre = new HomeTheatre();
        homeTheatre.startHomeTheatre();
        System.out.println("Home Theatre status: " + homeTheatre.getHomeTheatreStatus());

    }

}
