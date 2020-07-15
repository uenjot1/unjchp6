package unj.example.unjchp6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import unj.example.unjchp6.model.Product;
import unj.example.unjchp6.service.ProductService;

@RestController
@RequestMapping(value="v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	

    @RequestMapping(value="/{productId}",method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct( @PathVariable("productId") Integer productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
    	return productService.create(product);
    }

	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable("productId") Integer productId) {
		productService.delete(productId);
	}

}
