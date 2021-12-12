package com.mytechexp.design.creational.singleton;

import java.io.Serializable;

/*
* Singleton class to handle the race conditions
* */
public class SingleTonDeserializationProof implements Serializable {

    private static SingleTonDeserializationProof instance=null;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    int value;//this is to prove the deserialization issues
    private SingleTonDeserializationProof()
    {

    }

    public synchronized static SingleTonDeserializationProof getInstance()
    {
        if(instance==null)
         instance=new SingleTonDeserializationProof();
        return instance;
    }

    public static SingleTonDeserializationProof getInstanceDoubleCheck()
    {
        if(instance==null)
            synchronized(SingleTonDeserializationProof.class)
            {
                if(instance==null) {
                    instance = new SingleTonDeserializationProof();
                }
            }
        return instance;
    }
}
