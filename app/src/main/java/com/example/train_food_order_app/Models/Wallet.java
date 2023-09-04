package com.example.train_food_order_app.Models;

public class Wallet {

    private double balance;

    public Wallet(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deductAmount(double amount) {
        balance -= amount;
    }

}
