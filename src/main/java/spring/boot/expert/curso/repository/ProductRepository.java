package spring.boot.expert.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.expert.curso.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
