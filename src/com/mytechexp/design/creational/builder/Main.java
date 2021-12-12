package com.mytechexp.design.creational.builder;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args)
    {
        Car car1=new Car.CarBuilder("Hyndai").price(BigDecimal.valueOf(200000)).model("Asta").build();
        Car car2=new Car.CarBuilder("Hyndai").price(BigDecimal.valueOf(100000)).model("Sports").color("red").build();
        Car car3=new Car.CarBuilder("Hyndai").build();
        System.out.println(car1.toString());
        System.out.println(car2.toString());
        System.out.println(car3.toString());
    }
}
