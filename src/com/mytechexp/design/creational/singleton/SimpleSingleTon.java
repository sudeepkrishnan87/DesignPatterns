package com.mytechexp.design.creational.singleton;

/*To make simple singleton make private constructor
* so no other class can initialize this class
* */
public class SimpleSingleTon {
   private static SimpleSingleTon instance=null;
    private SimpleSingleTon()
    {

    }

    public static SimpleSingleTon getObject()
    {
        if(instance==null)
        instance= new SimpleSingleTon();
        return instance;
    }
}
