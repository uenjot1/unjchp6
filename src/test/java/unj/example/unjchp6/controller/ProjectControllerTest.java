package unj.example.unjchp6.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import unj.example.unjchp6.model.Currency;
import unj.example.unjchp6.model.Product;
import unj.example.unjchp6.service.ProductService;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {
	
	@Autowired
	private MockMvc mvc;

	@Mock
	private ProductService productService;

	@InjectMocks
	private ProductController prodcutController;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(prodcutController).build();
	}

	 @Test
	 void testPostProduct() throws Exception {

		 Product product = Product.of(1, "Unj", 10.5, Currency.EUR);

	        when(productService.create(product)).thenReturn(product);

	        mvc.perform(post("/v1/products")
	                .content(new ObjectMapper().writeValueAsString(product))
	                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.id", is(1)))
	                .andExpect(jsonPath("$.currency", is("EUR")))
	                .andExpect(jsonPath("$.price", is(10.5)))
	                .andExpect(jsonPath("$.name", is("Unj")));

	        verify(productService, times(1)).create(product);

	    }
	 
		@Test
		public void testDeleteProduct() throws Exception {
		
			mvc.perform(delete("/v1/products/4"))
				.andExpect(status().isNoContent());
	
			verify(productService, times(1)).delete(4);
		}
		
		@Test
		public void testGetProduct() throws Exception {
		
			Product product = Product.of(4, "Unj", 10.5, Currency.EUR);

			when(productService.findById(4)).thenReturn(product);
			mvc.perform(get("/v1/products/4"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(4)))
	            .andExpect(jsonPath("$.currency", is("EUR")))
	            .andExpect(jsonPath("$.price", is(10.5)))
	            .andExpect(jsonPath("$.name", is("Unj")));
	
			verify(productService, times(1)).findById(4);
		}

}
