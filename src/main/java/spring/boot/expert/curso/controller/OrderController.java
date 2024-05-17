package spring.boot.expert.curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import spring.boot.expert.curso.dto.OrderDTO;
import spring.boot.expert.curso.dto.OrderInfoDTO;
import spring.boot.expert.curso.dto.OrderStatusDTO;
import spring.boot.expert.curso.service.OrderService;

@RestController
@RequestMapping(value = "/orders", produces = "application/json")
@Tag(name = "Order", description = "Order API")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @Operation(summary = "Insert Order", description = "Insert Order, only for User", tags = {"POST"})
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OrderInfoDTO> insert (@RequestBody OrderDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.insert(dto));
    }

    @GetMapping(value = "/id/{id}")
    @Operation(summary = "Find Order by Id", description = "Find Order by Id, only for Admin and Master", tags = {"GET"})
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    public ResponseEntity<OrderInfoDTO> findById(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Find Order by Client", description = "Find Order by Client, only for User", tags = {"GET"})
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Page<OrderDTO>> findByClient(
        @PageableDefault(size = 10, page = 0, sort = { "id" }, direction = Direction.ASC) 
        Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findByClient(pageable));
    }

    @PutMapping(value = "/status/id/{id}")
    @Operation(summary = "Update Order Status", description = "Update Order Status, only for Admin and Master", tags = {"PUT"})
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    public ResponseEntity<OrderInfoDTO> update (@PathVariable("id") Integer id, @RequestBody OrderStatusDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.update(id, dto));
    }

    @DeleteMapping(value = "/id/{id}")
    @Operation(summary = "Delete Order by Id", description = "Delete Order by Id", tags = {"DELETE"})
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER', 'USER')")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Order canceled");
    }
  
}
