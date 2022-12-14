package com.example.restaurantmobileapp;
import java.util.HashMap;

public  class Product {
    public final static String path = "images/product/";
    private String name, type, detail;
    private String Id;
    private float price;
    Product(){
        name = "";
        type = "";
        detail = "";
        price = 0.0f;
    }
    public Product(String name, String type, String detail, float price) {
        this.name = name;
        this.type = type;
        this.detail = detail;
//        this.price = price;
        this.price = (float) (Math.round(price * 100.0) / 100.0);
        this.Id = name;
    }
    public String getId() {
        return Id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        this.Id = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
}
