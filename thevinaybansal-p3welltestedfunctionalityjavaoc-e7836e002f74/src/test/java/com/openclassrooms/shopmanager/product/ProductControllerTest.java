package com.openclassrooms.shopmanager.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {

private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webContext;
	
	@MockBean
	private ProductService productService;
	
	@Before
	public void setupMockmvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}
	
	@Test
	public void testCreateProductWithMissingName() throws Exception {
		
		mockMvc.perform(post("/admin/product")
	    .param("price", "25.5")
	    .param("quantity", "10"))
	    .andExpect(view().name("product"))
	    .andExpect(model().attributeHasFieldErrors("product", "name"))
	    .andExpect(status().isOk())
	    .andExpect(model().errorCount(1));
			       					
	}
	
	@Test
	public void testCreateProductWithPriceNotGreaterThanZero() throws Exception {
		
		mockMvc.perform(post("/admin/product")
		.param("name", "A20 Samsung 2019")
		.param("price", "0")
		.param("quantity", "5"))
		.andExpect(view().name("product"))
		.andExpect(model().attributeHasFieldErrors("product", "price"))
		.andExpect(status().isOk())
		.andExpect(model().errorCount(2));
	}
	
}
