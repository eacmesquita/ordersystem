package com.example.ordersystem.demo;

import java.util.List;

public class Order {
    private List<OrderItem> items;

    public void setItems (List<OrderItem> items) {
        this.items = items;
    }

    public List<OrderItem> getItems () {
        return this.items;
    }


}
