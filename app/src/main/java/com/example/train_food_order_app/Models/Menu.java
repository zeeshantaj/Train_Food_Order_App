package com.example.train_food_order_app.Models;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<MenuItems> items;

    public Menu() {
        this.items = new ArrayList<>();
    }

    public void addItem(MenuItems item) {
        items.add(item);
    }

    public List<MenuItems> getItems() {
        return items;
    }

}
