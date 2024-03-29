package spring.boot.expert.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.expert.curso.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{
    
}
