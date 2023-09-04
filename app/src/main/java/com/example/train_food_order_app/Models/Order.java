package com.example.train_food_order_app.Models;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String orderNumber;
    private String itemName;
    private String imageUrl;
    private int quantity;
    private double totalAmount;
    private String isAccepted;


    public Order(String orderNumber, String itemName, int quantity, double totalAmount,String imageUrl,String  isAccepted) {
        this.orderNumber = orderNumber;
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.imageUrl = imageUrl;
        this.isAccepted = isAccepted;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getImageUrl(){
        return imageUrl;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getIsAccepted() {
        return isAccepted;
    }
}
