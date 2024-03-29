package spring.boot.expert.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.expert.curso.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
    
}
