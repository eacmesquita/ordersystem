package com.example.ordersystem.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/order")
public class OrderController {
    OrderService orderService = new OrderService();

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody Order order) {
            OrderResponse response = orderService.processOrder(order);
            if (!response.getSuccess()) {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
            }
            return new ResponseEntity<>(response, HttpStatus.OK); 
    }

    @RequestMapping(method=RequestMethod.GET)
    public Map<Integer,Order> retrieveAllOrders(@RequestBody Order order) {
            return orderService.getAllOrders();  
    }

    @RequestMapping(value = "/{orderId}", method=RequestMethod.GET)
    public Order getOrderById(@PathVariable("orderId") int orderId) {
            return orderService.getOrderById(orderId);  
    }
    
}
