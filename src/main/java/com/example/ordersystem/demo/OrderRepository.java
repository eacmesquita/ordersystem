package com.example.ordersystem.demo;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository {
    static int INDEX = 0;
    Map<Integer, Order> ordersMap = new HashMap<>();

    public int saveOrder (Order order) {
        int id = INDEX++;
        ordersMap.put(id, order);
        return id;
    }

    public Order getOrderById(int id) {
        return ordersMap.get(id);
    }

    public Map<Integer,Order> getAllOrders() {
        return this.ordersMap;
    }
}
