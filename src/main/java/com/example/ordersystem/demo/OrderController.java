package com.example.ordersystem.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/order")
public class OrderController {
    OrderService orderService = new OrderService();

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody Order order) throws InvalidItemException {
            OrderResponse response = orderService.processOrder(order);
            if (!response.getSuccess()) {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
            }
            return new ResponseEntity<>(response, HttpStatus.OK); 
    }
    
}
