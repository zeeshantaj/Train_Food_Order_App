package com.example.train_food_order_app.Models;

public class OrderInfo {
    private String itemName,imageUrl, orderNumber;
    private String isAccepted;
    private int quantity;
    private double totalAmount;

    public OrderInfo(String itemName, String imageUrl, String orderNumber, String isAccepted, double totalAmount,int quantity) {
        this.itemName = itemName;
        this.imageUrl = imageUrl;
        this.orderNumber = orderNumber;
        this.isAccepted = isAccepted;
        this.totalAmount = totalAmount;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String isAccepted() {
        return isAccepted;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public int getQuantity(){
        return quantity;
    }
}
