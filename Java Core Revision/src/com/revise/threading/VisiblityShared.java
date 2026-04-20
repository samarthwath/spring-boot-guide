package com.revise.threading;

public class VisiblityShared {
    private boolean isThreadRunning = true;

    public void changeFlag() {
        isThreadRunning = false;
    }

    public boolean getFlag() {
        return isThreadRunning;
    }

}
