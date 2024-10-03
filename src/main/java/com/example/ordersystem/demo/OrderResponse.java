package com.example.ordersystem.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) 
public class OrderResponse {
    @JsonIgnore
    boolean success;
    String orderSummary;
    Float totalOrderCost;
    Integer orderId;

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

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void createOrderSummary(Order order) {
        String summary = "You ordered: ";
        for (OrderItem item : order.getItems()) {
            summary += String.format("%d of %s | ", item.getQuantity(), item.getItemName());
        }
        this.setOrderSummary(summary);
    }
}
