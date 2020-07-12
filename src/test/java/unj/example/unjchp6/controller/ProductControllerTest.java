package unj.example.unjchp6.controller;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

import unj.example.unjchp6.model.Product;

@Disabled
class ProductControllerTest {

	private RestTemplate restTemplate = new RestTemplate();
	
	@Test
	void test() {
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("unj", "12345"));
		restTemplate.exchange("http://localhost:9022/products", 
	            HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {})
	        .getBody();

	}
}
