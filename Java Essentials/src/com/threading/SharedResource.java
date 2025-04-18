package com.threading;

public class SharedResource {

    //Without volatile keyword.
//    private boolean flag = false;

    //With volatile keyword.
    private volatile boolean flag = false;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return this.flag;
    }
}
