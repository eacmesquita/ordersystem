package com.example.ordersystem.demo;

import java.util.HashMap;
import java.util.Map;

public class OrderService {
    private String INVALID_ORDER = "Order details are invalid";

    Map<String, Float> itemToPriceMapping = new HashMap<>() {{
        put("apple", 0.6f);
        put("orange", 0.25f);
    }};

    public OrderResponse processOrder (Order submittedOrder) {
        OrderResponse response = new OrderResponse();
        float totalCost = 0.0f;
        for (OrderItem item : submittedOrder.getItems()) {
            float itemPrice = calculatePriceOfItem(item);
            if (itemPrice < 0.0f) {
                response.setOrderSummary(INVALID_ORDER);
                response.setSuccess(false);
                return response;
            }
            totalCost += itemPrice;
        }
        response.setTotalOrderCost(totalCost);
        response.createOrderSummary(submittedOrder);
        response.setSuccess(true);
        return response;
    }

    /*
     * returns -1 if an invalid item is passed
     */
    public Float calculatePriceOfItem(OrderItem item) {
        if (!itemToPriceMapping.containsKey(item.getItemName()) || item.getQuantity() < 1) {
            return -1.0F;
        }
        float itemPrice = itemToPriceMapping.get(item.getItemName());
        //to be updated with offers later
        return item.getQuantity() * itemPrice;
    }
}
