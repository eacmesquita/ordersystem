package com.example.ordersystem.demo;


public class OrderItem {
    int itemId;
    String itemName;
    int quantity;

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

    public OrderItem (String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }
}
