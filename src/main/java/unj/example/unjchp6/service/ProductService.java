package unj.example.unjchp6.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import unj.example.unjchp6.model.Product;
import unj.example.unjchp6.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	public Product create(Product product) {
		return productRepository.save(product);
	}
	
	public Product findById(Integer productId) {		
		Supplier<NoSuchElementException> s = () -> new NoSuchElementException("Product with id:"+productId+" not found");
		return productRepository.findById(productId).orElseThrow(s);
	}
	
	public Product update(Product product) {
		return productRepository.save(product);
	}

	public void delete(Integer productId) {
		productRepository.deleteById(productId);
	}
}
