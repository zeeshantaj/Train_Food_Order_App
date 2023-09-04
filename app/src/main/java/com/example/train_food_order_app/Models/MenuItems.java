package com.example.train_food_order_app.Models;

public class MenuItems {
    private String itemName;
    private String description;
    private double price;
    //private int ImageResourceId;
    private String imageUrl;

    public MenuItems() {
    }

    public MenuItems(String itemName, String description, double price, String imageUrl) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
