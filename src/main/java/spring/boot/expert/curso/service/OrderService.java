package spring.boot.expert.curso.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.expert.curso.dto.OrderDTO;
import spring.boot.expert.curso.dto.OrderInfoDTO;
import spring.boot.expert.curso.dto.OrderItemDTO;
import spring.boot.expert.curso.model.Client;
import spring.boot.expert.curso.model.Order;
import spring.boot.expert.curso.model.OrderItem;
import spring.boot.expert.curso.model.Product;
import spring.boot.expert.curso.repository.ClientRepository;
import spring.boot.expert.curso.repository.OrderItemRepository;
import spring.boot.expert.curso.repository.OrderRepository;
import spring.boot.expert.curso.repository.ProductRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public OrderDTO insert(OrderDTO orderDTO){
        Order order = new Order();
        order.setTotal(orderDTO.getTotal());
        order.setDate(Instant.now());
        order.setClient(clientID(orderDTO));

        List<OrderItem> items = items(order, orderDTO.getItems());

        order = orderRepository.save(order);
        orderItemRepository.saveAll(items);

        order.setItems(items);
        
        return new OrderDTO(order.getId(), order.getClient().getId(), order.getDate(), order.getTotal(), order.getItems().stream().map(x -> new OrderItemDTO(x.getProduct().getId(), x.getQuantity())).collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public OrderInfoDTO findById(Integer id){
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        OrderDTO dto = orderDTO(order); 
        Client client = clientID(dto);
        return new OrderInfoDTO(order.getId(), order.getClient().getId(), client.getName(), order.getDate(), order.getTotal(), order.getItems().stream().map(x -> new OrderItemDTO(x.getProduct().getId(), x.getQuantity())).collect(Collectors.toList()));
    }

    public Client clientID(OrderDTO orderDTO){
        Integer idClient = orderDTO.getClient();
        Client client = clientRepository.findById(idClient)
            .orElseThrow(() -> new RuntimeException("Client not found"));
        return client;
    }

    
    public List<OrderItem> items(Order order, List<OrderItemDTO> items){
        if(items.isEmpty()){
            throw new RuntimeException("Order must have at least one item");
        }
        return items.stream().map(dto -> {
            Integer idProduct = dto.getProduct();
            Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new RuntimeException("Product not found"));
            
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(dto.getQuantity());
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList());
    }

    public OrderDTO orderDTO(Order order){
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setClient(order.getClient().getId());
        dto.setDate(order.getDate());
        dto.setTotal(order.getTotal());
        dto.setItems(order.getItems().stream().map(x -> new OrderItemDTO(x.getProduct().getId(), x.getQuantity())).collect(Collectors.toList()));
        return dto;
    }

}