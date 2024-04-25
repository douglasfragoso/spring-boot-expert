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

import spring.boot.expert.curso.dto.ProductDTO;
import spring.boot.expert.curso.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(
        @PageableDefault(size = 10, page = 0, sort = { "name" }, direction = Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll(pageable));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert (@RequestBody ProductDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.insert(dto));
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Integer id, @RequestBody ProductDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, dto));
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted");
    }
}
