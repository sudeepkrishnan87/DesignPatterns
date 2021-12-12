package com.mytechexp.design.creational.singleton;

import java.io.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        SimpleSingleTon instance1=SimpleSingleTon.getObject();
        SimpleSingleTon instance2=SimpleSingleTon.getObject();
        System.out.println("###SimpleSingleton#########");
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());

        System.out.println("###########Race Condition###########");
        Runnable run=()->{
            SingleTonRaceCondition instance=SingleTonRaceCondition.getInstance();
            System.out.println("Race Condition#"+instance.hashCode());
        };
        Thread t1=new Thread(run);
        Thread t2=new Thread(run);
        t1.start();
        t2.start();
        System.out.println("###########Race Condition with double check###########");
        Runnable run2=()->{SingleTonRaceCondition instance=SingleTonRaceCondition.getInstanceDoubleCheck();
            System.out.println("Race Condition double check"+instance.hashCode());
        };

        Thread t3=new Thread(run2);
        Thread t4=new Thread(run2);
        t3.start();
        t4.start();
        /*
        * issue with the above code is in the area of serialization lets see how we can prove it
        *
        * */

        SingleTonDeserializationProof testDeserialization=SingleTonDeserializationProof.getInstance();
        testDeserialization.setValue(100);
        try {
            FileOutputStream fileout =new FileOutputStream("out.ser");
            ObjectOutputStream out=new ObjectOutputStream(fileout);
            out.writeObject(testDeserialization);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
// Now change value for SingleTon
        testDeserialization.setValue(200);
        SingleTonDeserializationProof testDeserializationnew=null;
        try {
            FileInputStream filein=new FileInputStream("out.ser");
            ObjectInputStream in=new ObjectInputStream(filein);
            testDeserializationnew=(SingleTonDeserializationProof)in.readObject();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Hash code for Singleton 1"+testDeserialization.hashCode());
        System.out.println("Hash code for Singleton 1"+testDeserializationnew.hashCode());
        if(testDeserialization==testDeserializationnew)
        {
            System.out.println("Same Object after deserialization");
        }
        else
        {
            System.out.println("different Object after deserialization");
        }
        System.out.println("###########Fix Serialization###########");
        SingletonPreventSerialization serialinstance1=SingletonPreventSerialization.INSTANCE;
        SingletonPreventSerialization serialinstance2=SingletonPreventSerialization.INSTANCE;
        System.out.println("Serialise Instance"+serialinstance1.hashCode());
        System.out.println("Serialise Instance"+serialinstance2.hashCode());


        /*
        * Now test the serialization and deserialization
        * */
        serialinstance1.setValue(100);

        try {
            FileOutputStream fileoutput=new FileOutputStream("fixout.ser");
            ObjectOutputStream outputStream=new ObjectOutputStream(fileoutput);
            outputStream.writeObject(serialinstance1);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        serialinstance1.setValue(400);
        SingletonPreventSerialization preventSerialization=null;
        try {
            FileInputStream infile=new FileInputStream("fixout.ser");
            ObjectInputStream inputStream=new ObjectInputStream(infile);
            preventSerialization=(SingletonPreventSerialization)inputStream.readObject();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(preventSerialization.hashCode());
        System.out.println(serialinstance1.hashCode());

        System.out.println(preventSerialization.getValue());
        System.out.println(serialinstance1.getValue());
        if(preventSerialization==serialinstance1)
            System.out.println("Same Object");
        else
            System.out.println("Different Object");
    }
}

