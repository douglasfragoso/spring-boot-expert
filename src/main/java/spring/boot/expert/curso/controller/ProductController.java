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
import jakarta.validation.Valid;
import spring.boot.expert.curso.dto.ProductDTO;
import spring.boot.expert.curso.service.ProductService;

@RestController
@RequestMapping(value = "/products", produces = "application/json")
@Tag(name = "Product", description = "Product API")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    @Operation(summary = "Insert Product", description = "Insert Product, only for Admin and Master", tags = {"POST"})
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.insert(dto));
    }

    @GetMapping
    @Operation(summary = "Find All Products", description = "Find All Products", tags = {"GET"})
    public ResponseEntity<Page<ProductDTO>> findAll(
            @PageableDefault(size = 10, page = 0, sort = { "name" }, direction = Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll(pageable));
    }

    @GetMapping(value = "/id/{id}")
    @Operation(summary = "Find Product by Id", description = "Find Product by Id, only for Admin and Master", tags = {"GET"})
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    @GetMapping(value = "/name/{name}")
    @Operation(summary = "Find Product by Name", description = "Find Product by name", tags = {"GET"})
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER', 'USER')")
    public ResponseEntity<Page<ProductDTO>> findByNameLike(
            @PageableDefault(size = 10, page = 0, sort = { "name" }, direction = Direction.ASC) Pageable pageable,
            @PathVariable("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByNameLike(pageable, name));
    }

    @PutMapping(value = "/id/{id}")
    @Operation(summary = "Update Product", description = "Update Product, only for Admin and User", tags = {"PUT"})
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    public ResponseEntity<ProductDTO> update(@PathVariable Integer id, @RequestBody ProductDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, dto));
    }

    @DeleteMapping(value = "/id/{id}")
    @Operation(summary = "Delete Product by Id", description = "Delete Product by Id", tags = {"DELETE"})
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted");
    }

    
}
