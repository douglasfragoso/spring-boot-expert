package spring.boot.expert.curso.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.boot.expert.curso.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT order FROM Order order WHERE order.client.id = :client")
    Page<Order> findByClient(Pageable pegeable, Integer client);

}
