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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import spring.boot.expert.curso.controller.exception.StandartError;
import spring.boot.expert.curso.controller.exception.ValidationError;
import spring.boot.expert.curso.dto.ProductDTO;
import spring.boot.expert.curso.service.ProductService;

@RestController
@RequestMapping(value = "/products", produces = "application/json")
@Tag(name = "Product", description = "Product API")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @PostMapping
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Successfully created", content = @Content(schema = @Schema(implementation = ProductDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ValidationError.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ValidationError.class)))})
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    @Operation(summary = "Insert Product", description = "Insert Product, only for Admin and Master", tags = {"POST"})
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.insert(dto));
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Successfully find all", content = @Content(schema = @Schema(implementation = ProductDTO.class)))
    @Operation(summary = "Find All Products", description = "Find All Products", tags = {"GET"})
    public ResponseEntity<Page<ProductDTO>> findAll(
            @PageableDefault(size = 10, page = 0, sort = { "name" }, direction = Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll(pageable));
    }

    @GetMapping(value = "/id/{id}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully find by Id", content = @Content(schema = @Schema(implementation = ProductDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ValidationError.class))),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = StandartError.class)))})
    @Operation(summary = "Find Product by Id", description = "Find Product by Id, only for Admin and Master", tags = {"GET"})
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    @GetMapping(value = "/name/{name}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully find by name", content = @Content(schema = @Schema(implementation = ProductDTO.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ValidationError.class))),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = StandartError.class)))})
    @Operation(summary = "Find Product by Name", description = "Find Product by name", tags = {"GET"})
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER', 'USER')")
    public ResponseEntity<Page<ProductDTO>> findByNameLike(
            @PageableDefault(size = 10, page = 0, sort = { "name" }, direction = Direction.ASC) Pageable pageable,
            @PathVariable("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByNameLike(pageable, name));
    }

    @PutMapping(value = "/id/{id}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully update", content = @Content(schema = @Schema(implementation = ProductDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ValidationError.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ValidationError.class))),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = StandartError.class)))})
    @Operation(summary = "Update Product", description = "Update Product, only for Admin and User", tags = {"PUT"})
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    public ResponseEntity<ProductDTO> update(@PathVariable Integer id, @RequestBody ProductDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, dto));
    }

    @DeleteMapping(value = "/id/{id}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully delete", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ValidationError.class))),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = StandartError.class)))})
    @Operation(summary = "Delete Product by Id", description = "Delete Product by Id", tags = {"DELETE"})
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted");
    }

    
}
