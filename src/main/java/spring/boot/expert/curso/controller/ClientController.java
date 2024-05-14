package spring.boot.expert.curso.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spring.boot.expert.curso.dto.ClientDTO;
import spring.boot.expert.curso.dto.ClientProfileDTO;
import spring.boot.expert.curso.service.ClientService;

@RestController
@RequestMapping(value = "/clients", produces = "application/json")
public class ClientController {

    @Autowired
    private ClientService clientService;

    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    public ResponseEntity<Page<ClientDTO>> findAll(
        @PageableDefault(size = 10, page = 0, sort = { "name" }, direction = Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll(pageable));
    }

    @GetMapping(value = "/id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    public ResponseEntity<ClientDTO> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findById(id));
    }

    @GetMapping(value = "/name/{name}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    public ResponseEntity<Page<ClientDTO>> findByNameLike(
        @PageableDefault(size = 10, page = 0, sort = { "name" }, direction = Direction.ASC) Pageable pageable,
        @PathVariable("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findByNameLike(pageable, name));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.insert(dto));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<String> update(@RequestBody ClientDTO dto) {
        return ResponseEntity.status(HttpStatus.FOUND).body("Profile updated successfully");
    }

    @PutMapping(value = "/id/{id}")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<ClientDTO> updateProfile(@PathVariable("id")Integer id, @RequestBody ClientProfileDTO dto) {
        return ResponseEntity.status(HttpStatus.FOUND).body(clientService.updateMaster(id, dto));
    }

    @DeleteMapping(value = "/id/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

}
