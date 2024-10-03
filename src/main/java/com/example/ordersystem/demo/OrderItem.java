package com.example.ordersystem.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderItem {
    int itemId;
    String itemName;
    int quantity;
    @JsonIgnore
    float price;

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName () {
        return this.itemName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity () {
        return this.quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice () {
        return this.price;
    }

    public OrderItem (String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }
}
