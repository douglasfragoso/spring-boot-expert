package spring.boot.expert.curso.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.expert.curso.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByName(String name);

    Page<Product> findByNameLikeIgnoreCase(Pageable pegeable, String name);


    
}
