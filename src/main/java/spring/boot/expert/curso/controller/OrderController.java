package spring.boot.expert.curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.expert.curso.dto.OrderDTO;
import spring.boot.expert.curso.dto.OrderInfoDTO;
import spring.boot.expert.curso.dto.OrderStatusDTO;
import spring.boot.expert.curso.service.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderInfoDTO> insert (@RequestBody OrderDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.insert(dto));
    }
    
    @GetMapping(value = "/id/{id}")
    public ResponseEntity<OrderInfoDTO> findById(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findById(id));
    }

    @GetMapping(value = "/client/{id}")
    public ResponseEntity<Page<OrderDTO>> findByClient(
        @PageableDefault(size = 10, page = 0, sort = { "id" }, direction = Direction.ASC) 
        Pageable pageable, @PathVariable("id") Integer client) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findByClient(pageable, client));
    }

    @PutMapping(value = "/status/id/{id}")
    public ResponseEntity<OrderInfoDTO> update (@PathVariable("id") Integer id, @RequestBody OrderStatusDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.update(id, dto));
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Order deleted");
    }
  
}
