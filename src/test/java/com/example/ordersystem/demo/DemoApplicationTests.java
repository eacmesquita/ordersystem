package com.example.ordersystem.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

    OrderRepository repository = new OrderRepository();

	@Autowired
    private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
public void testPlacingOrder() throws Exception {
    String jsonRequest = "{\"items\":[{\"itemName\":\"apple\",\"quantity\":4},{\"itemName\":\"orange\",\"quantity\":5}]}";
    mockMvc.perform(post("/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.totalOrderCost").value(2.2));
}

public void testPlacingInvalidOrder() throws Exception {
    String jsonRequest = "{\"items\":[{\"itemName\":\"apple\",\"quantity\":4},{\"itemName\":\"orange\",\"quantity\":5},{\"itemName\":\"banana\",\"quantity\":5}]}";
    mockMvc.perform(post("/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest))
        .andExpect(status().isBadRequest());
}

public void testOrderIdIsReturned() throws Exception {
    String jsonRequest = "{\"items\":[{\"itemName\":\"apple\",\"quantity\":4},{\"itemName\":\"orange\",\"quantity\":5}]}";
    mockMvc.perform(post("/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.orderId").exists());
}

public void testAllOrdersEndpoint() throws Exception {
    mockMvc.perform(get("/order")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
}

public void testOrderByIdEndpoint() throws Exception {
    String jsonRequest = "{\"items\":[{\"itemName\":\"apple\",\"quantity\":4},{\"itemName\":\"orange\",\"quantity\":5}]}";
    mockMvc.perform(get("/order/0")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest))
        .andExpect(status().isOk());
}

public void  testCalculatePriceWithOffers() {
	OrderItem oiFive = new OrderItem("orange", 5);
	OrderService os = new OrderService();
	float orangePriceForFive = os.calculatePriceOfItem(oiFive);
	OrderItem oiSix = new OrderItem("orange", 6);
	float orangePriceForSix = os.calculatePriceOfItem(oiSix);
	assertEquals(orangePriceForFive, orangePriceForSix);
}

public void testSaveRepository() {
    Order order = new Order();
    repository.saveOrder(order);
    assertNotNull(repository.getOrderById(0));
    assertNotNull(repository.getAllOrders());
}
}
