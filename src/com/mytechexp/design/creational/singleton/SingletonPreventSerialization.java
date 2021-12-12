package com.mytechexp.design.creational.singleton;

public enum SingletonPreventSerialization {
    INSTANCE;

    int value;
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }



}
