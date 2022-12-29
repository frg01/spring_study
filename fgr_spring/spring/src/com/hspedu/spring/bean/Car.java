package com.hspedu.spring.bean;

import java.math.BigDecimal;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public class Car {
    private Integer carId;
    private String name;
    private String brand;
    private BigDecimal price;


    public Car() {
    }

    public Car(Integer carId, String name, String brand, BigDecimal price) {
        this.carId = carId;
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }

}
