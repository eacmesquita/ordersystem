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
    public float calculatePriceOfItem(OrderItem item) {
        if (!itemToPriceMapping.containsKey(item.getItemName()) || item.getQuantity() < 1) {
            return -1.0F;
        }
        float itemPrice = itemToPriceMapping.get(item.getItemName());
        item.setPrice(itemPrice);
        return calculatePriceOfItemWithOffers(item);
    }

    public float calculatePriceOfItemWithOffers(OrderItem item) {
        float priceWithOffers = 0.0f;
        switch(item.getItemName()) {
            //for apples it is buy 1 get 1 so price is for (half the number of applies + any odd numbered apple)
            case "apple":
                priceWithOffers = (item.getQuantity()/2 + item.getQuantity()%2) * item.getPrice();
                break;
            case "orange":
            //oranges are three for the price of 2 - another term is needed for the modulus of 3
                priceWithOffers = ((item.getQuantity()/3) * 2 * item.getPrice()) + (item.getQuantity()%3) * item.getPrice();
                break;
            default: 
            //no offers for this item
                priceWithOffers = item.getQuantity() * item.getPrice();
                break;
        }
        return priceWithOffers;
    }
}
