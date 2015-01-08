package com.base.engine.core;


public abstract class ReferenceCounter {

    private int refCount;

    public ReferenceCounter() {
        refCount = 1;
    }

    public void addReference() {
        refCount++;
    }

    public boolean removeReference() {
        refCount --;
        return refCount == 0;
    }

    public int getReferenceCount() {
        return refCount;
    }
}
