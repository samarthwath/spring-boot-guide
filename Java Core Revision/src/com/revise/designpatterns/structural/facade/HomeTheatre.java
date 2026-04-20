package com.revise.designpatterns.structural.facade;

import java.util.Map;

public class HomeTheatre {
    private DVDPlayer dvdPlayer;
    private Projector projector;
    private SoundSystem soundSystem;

    public HomeTheatre() {
        this.dvdPlayer = new DVDPlayer();
        this.projector = new Projector();
        this.soundSystem = new SoundSystem();
    }

    public void startHomeTheatre() {
        dvdPlayer.setStatus("ON");
        soundSystem.setVolume(10);
        projector.setStatus("ON");
    }

    public Map<String, Object> getHomeTheatreStatus() {
        return Map.of("dvdPlayerStatus", dvdPlayer.getStatus(),
                "projectorStatus", projector.getStatus(),
                "volume", soundSystem.getVolume()
        );
    }


}
