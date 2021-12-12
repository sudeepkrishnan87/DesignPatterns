package com.mytechexp.design.creational.singleton;

/*
* Singleton class to handle the race conditions
* */
public class SingleTonRaceCondition {

    private static SingleTonRaceCondition instance=null;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    int value;//this is to prove the deserialization issues
    private SingleTonRaceCondition()
    {

    }

    public synchronized static SingleTonRaceCondition getInstance()
    {
        if(instance==null)
         instance=new SingleTonRaceCondition();
        return instance;
    }

    public static SingleTonRaceCondition getInstanceDoubleCheck()
    {
        if(instance==null)
            synchronized(SingleTonRaceCondition.class)
            {
                if(instance==null) {
                    instance = new SingleTonRaceCondition();
                }
            }
        return instance;
    }
}
