package com.shopping.food;

class Food {
    private String name;
    private double price;
    private Food() {

    }
    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String toString() {
        return "名字: " + name + " 价格: " + price + "元";
    }
}