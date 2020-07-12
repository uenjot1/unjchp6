package unj.example.unjchp6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import unj.example.unjchp6.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
