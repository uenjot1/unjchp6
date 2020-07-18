package unj.example.unjchp6.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import unj.example.authentication.Unjchp6AuthenticationProvider;
import unj.example.unjchp6.model.Currency;
import unj.example.unjchp6.model.Product;
import unj.example.unjchp6.repository.AuthorityRepository;
import unj.example.unjchp6.repository.ProductRepository;
import unj.example.unjchp6.repository.UserRepository;
import unj.example.unjchp6.service.ProductService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerAuthorizationIT {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private Unjchp6AuthenticationProvider authenticationProvider;

	@MockBean
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private AuthorityRepository authorityRepository;

	@Test
	@WithMockUser
	public void testGetProduct() throws Exception {
		Product product = Product.of(1, "Unj", 10.5, Currency.EUR);

		when(productService.create(product)).thenReturn(product);

		mvc.perform(get("/v1/products/1")).andExpect(status().isOk());

	}

	@Test
	@WithMockUser(authorities = "WRITE")
	void testPostProduct() throws Exception {
		Product product = Product.of(1, "Unj", 10.5, Currency.EUR);

		mvc.perform(post("/v1/products")
				.content(new ObjectMapper().writeValueAsString(product))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}
	
	@Test
	@WithMockUser(authorities = "READ")
	void testPostProductUnauthorised() throws Exception {
		Product product = Product.of(1, "Unj", 10.5, Currency.EUR);

		mvc.perform(post("/v1/products")
				.content(new ObjectMapper().writeValueAsString(product))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());

	}
}
