package com.cms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class AuthControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

	//Test whether the endpoint /app/sign is successful
	@Test
	public void test202RestApiCallForTokenGeneration() throws Exception {
		
	} 
	
	//Test whether the endpoint /app/validateToken/{authorization} is successful
	@Test
	public void test203RestApiCallForValidatingToken() throws Exception{
		String token = "your_token_here";
        mockMvc.perform(MockMvcRequestBuilders.get("/app/validateToken/{authorization}", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk());

			
	} 
	
	

}