package com.revise.threading;

public class VisiblitySharedFix {
    private volatile boolean isThreadRunning = true;

    public boolean getFlag() {
        return isThreadRunning;
    }

    public void changeFlag() {
        isThreadRunning = false;
    }

}
