package spring.boot.expert.curso.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.expert.curso.dto.OrderDTO;
import spring.boot.expert.curso.dto.OrderInfoDTO;
import spring.boot.expert.curso.dto.OrderItemDTO;
import spring.boot.expert.curso.dto.OrderItemInfoDTO;
import spring.boot.expert.curso.dto.OrderStatusDTO;
import spring.boot.expert.curso.enums.OrderStatus;
import spring.boot.expert.curso.model.Client;
import spring.boot.expert.curso.model.Order;
import spring.boot.expert.curso.model.OrderItem;
import spring.boot.expert.curso.model.Product;
import spring.boot.expert.curso.repository.ClientRepository;
import spring.boot.expert.curso.repository.OrderItemRepository;
import spring.boot.expert.curso.repository.OrderRepository;
import spring.boot.expert.curso.repository.ProductRepository;
import spring.boot.expert.curso.service.exception.ExceptionBusinessRules;

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
    public OrderInfoDTO insert(OrderDTO orderDTO) {
        Order order = new Order();
        Client client = clientId(orderDTO);

        order.setDate(Instant.now());
        order.setClient(clientId(orderDTO));
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        List<OrderItem> items = items(order, orderDTO.getItems());

        order = orderRepository.save(order);
        orderItemRepository.saveAll(items);

        order.addItems(items);

        order.setTotal(items);

        return new OrderInfoDTO(order.getId(), order.getClient().getId(), client.getName(), order.getDate(),
                order.getTotal(), order.getStatus(), itemsInfo(order, orderDTO.getItems()));
    }

    @Transactional
    public void delete(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ExceptionBusinessRules("Order not found, id does not exist: " + id));
        orderRepository.delete(order);
    }

    @Transactional
    public OrderInfoDTO update(Integer id, OrderStatusDTO dto){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ExceptionBusinessRules("Order not found, id does not exist: " + id));
        order.setStatus(OrderStatus.valueOf(dto.getStatus()));
        order = orderRepository.save(order);
        OrderDTO orderDTO = turnDTO(order);
        return new OrderInfoDTO(order.getId(), order.getClient().getId(), order.getClient().getName(), order.getDate(),
                order.getTotal(), order.getStatus(), itemsInfo(order, orderDTO.getItems()));
    }

    @Transactional(readOnly = true)
    public OrderInfoDTO findById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ExceptionBusinessRules("Order not found, id does not exist: " + id));
        OrderDTO orderDTO = turnDTO(order);
        Client client = clientId(orderDTO);
        return new OrderInfoDTO(order.getId(), order.getClient().getId(), client.getName(), order.getDate(),
                order.getTotal(), order.getStatus(), itemsInfo(order, orderDTO.getItems()));
    }

    @Transactional(readOnly = true)
    public Page<OrderDTO> findByClient(Pageable pageable, Integer client) {
        Page<Order> list = orderRepository.findByClient(pageable, client);
        if (list.isEmpty()) {
            throw new ExceptionBusinessRules("Client not found, id does not exist: " + client);
        }
        return list.map(x -> turnDTO(x));
    }

    public Client clientId(OrderDTO orderDTO) {
        Integer idClient = orderDTO.getClient();
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ExceptionBusinessRules("Client not found, id does not exist: " + idClient));
        return client;
    }

    public List<OrderItem> items(Order order, List<OrderItemDTO> items) {
        if (items.isEmpty()) {
            throw new ExceptionBusinessRules("Order must have at least one item");
        }
        return items.stream().map(dto -> {
            Integer idProduct = dto.getProduct();
            Product product = productRepository.findById(idProduct)
                    .orElseThrow(() -> new ExceptionBusinessRules("Product not found, id does not exist: " + idProduct));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(dto.getQuantity());
            orderItem.setOrder(order);
            return orderItem;
        }).collect(Collectors.toList());
    }

    public List<OrderItemInfoDTO> itemsInfo(Order order, List<OrderItemDTO> items) {
        if (items.isEmpty()) {
            throw new ExceptionBusinessRules("Order must have at least one item");
        }
        return items.stream().map(dto -> {
            Integer idProduct = dto.getProduct();
            Product product = productRepository.findById(idProduct)
                    .orElseThrow(() -> new ExceptionBusinessRules("Product not found, id does not exist: " + idProduct));

            OrderItemInfoDTO orderItem = new OrderItemInfoDTO();
            orderItem.setProduct(idProduct);
            orderItem.setProductName(product.getName());
            orderItem.setQuantity(dto.getQuantity());
            return orderItem;
        }).collect(Collectors.toList());
    }

    public OrderDTO turnDTO(Order order) {
        return new OrderDTO(order.getId(), order.getClient().getId(), order.getDate(), order.getTotal(),
                order.getStatus(),
                order.getItems().stream().map(x -> new OrderItemDTO(x.getProduct().getId(), x.getQuantity()))
                        .collect(Collectors.toList()));
    }

}
