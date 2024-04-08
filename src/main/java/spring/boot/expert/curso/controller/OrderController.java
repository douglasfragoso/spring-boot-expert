package spring.boot.expert.curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.expert.curso.dto.OrderDTO;
import spring.boot.expert.curso.service.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> insert (@RequestBody OrderDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.insert(dto));
    }
    
}
