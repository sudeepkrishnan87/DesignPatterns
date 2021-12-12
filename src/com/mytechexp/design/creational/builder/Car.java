package com.mytechexp.design.creational.builder;

import java.math.BigDecimal;
/*
* Builder design pattern to maintain immutability
* Good solution to create complex object states which have enormous attributes
*
* */
public class Car {
    private String name;
    private BigDecimal price;
    private String model;
    private int year;
    private String color;

    /*
    Only setter methods else it will cause immutability issues
    * */

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                '}';
    }

    public Car(CarBuilder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.model = builder.model;
        this.year = builder.year;
        this.color = builder.color;
    }

    public static class CarBuilder
    {


        private String name;
        private BigDecimal price;
        private String model;
        private int year;
        private String color;
//Mandatory field with constructor
        public CarBuilder (String name) {
            this.name = name;
        }

        public CarBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public CarBuilder year(int year) {
            this.year = year;
            return this;
        }
        public CarBuilder model(String model) {
            this.model = model;
            return this;
        }
        public CarBuilder color(String color) {
            this.color = color;
            return this;
        }

        public Car build()
        {
            Car car=new Car(this);
            return car;
        }
    }
}
