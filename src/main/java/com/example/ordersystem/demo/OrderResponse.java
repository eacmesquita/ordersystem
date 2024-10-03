package com.example.ordersystem.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderResponse {
    @JsonIgnore
    boolean success;
    String orderSummary;
    Float totalOrderCost = null;

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getOrderSummary() {
        return this.orderSummary;
    }

    public void setOrderSummary(String orderSummary) {
        this.orderSummary = orderSummary;
    }

    public Float getTotalOrderCost() {
        return this.totalOrderCost;
    }

    public void setTotalOrderCost(Float totalOrderCost) {
        this.totalOrderCost = totalOrderCost;
    }

    public void createOrderSummary(Order order) {
        String summary = "You ordered: ";
        for (OrderItem item : order.getItems()) {
            summary += String.format("%d of %s | ", item.getQuantity(), item.getItemName());
        }
        this.setOrderSummary(summary);
    }
}
