package unj.example.unjchp6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import unj.example.unjchp6.model.Product;
import unj.example.unjchp6.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getProducts(){
		return productService.findAll();
	}

}
