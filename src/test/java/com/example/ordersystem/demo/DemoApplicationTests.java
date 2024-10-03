package com.example.ordersystem.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

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
        .andExpect(jsonPath("$.totalOrderCost").value(3.65));
}

public void testPlacingInvalidOrder() throws Exception {
    String jsonRequest = "{\"items\":[{\"itemName\":\"apple\",\"quantity\":4},{\"itemName\":\"orange\",\"quantity\":5},{\"itemName\":\"banana\",\"quantity\":5}]}";
    mockMvc.perform(post("/order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest))
        .andExpect(status().isBadRequest());
}


}
