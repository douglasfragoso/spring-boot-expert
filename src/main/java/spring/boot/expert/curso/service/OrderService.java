package spring.boot.expert.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.expert.curso.dto.OrderDTO;
import spring.boot.expert.curso.model.Order;
import spring.boot.expert.curso.repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public OrderDTO insert (OrderDTO dto){
        Order order = new Order();
        order.setClient(dto.getClient());
        order.setTotal(dto.getTotal());
        order.setDate(dto.getDate());
        order = orderRepository.save(order);
        return new OrderDTO(order.getId(), order.getClient(), order.getDate(), order.getTotal());
    }
}
