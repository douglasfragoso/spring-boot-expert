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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import spring.boot.expert.curso.dto.ClientDTO;
import spring.boot.expert.curso.dto.ClientProfileDTO;
import spring.boot.expert.curso.service.ClientService;

@RestController
@RequestMapping(value = "/clients", produces = "application/json")
@Tag(name = "Client", description = "Client API")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/register")
    @Operation(summary = "Insert Client", description = "Insert client", tags = {"POST"})
    public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.insert(dto));
    }

    @GetMapping
    @Operation(summary = "Find All Clients", description = "Find All Clients, only for Admin and Master", tags = {"GET"})
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    public ResponseEntity<Page<ClientDTO>> findAll(
        @PageableDefault(size = 10, page = 0, sort = { "name" }, direction = Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll(pageable));
    }

    @GetMapping(value = "/id/{id}")
    @Operation(summary = "Find Client", description = "Find Client by Id, only for Admin and Master", tags = {"GET"})
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    public ResponseEntity<ClientDTO> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findById(id));
    }

    @GetMapping(value = "/name/{name}")
    @Operation(summary = "Find Clients by Name", description = "Find Client by Name, only for Admin and Master", tags = {"GET"})
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    public ResponseEntity<Page<ClientDTO>> findByNameLike(
        @PageableDefault(size = 10, page = 0, sort = { "name" }, direction = Direction.ASC) Pageable pageable,
        @PathVariable("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findByNameLike(pageable, name));
    }

    @PutMapping
    @Operation(summary = "Update Client", description = "Update Client, only for Admin and User", tags = {"PUT"})
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<String> update(@RequestBody ClientDTO dto) {
        return ResponseEntity.status(HttpStatus.FOUND).body("Profile updated successfully");
    }

    @PutMapping(value = "/id/{id}")
    @Operation(summary = "Update Profile", description = "Update Profile, only for Master", tags = {"PUT"})
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity<ClientDTO> updateProfile(@PathVariable("id")Integer id, @RequestBody ClientProfileDTO dto) {
        return ResponseEntity.status(HttpStatus.FOUND).body(clientService.updateMaster(id, dto));
    }

    @DeleteMapping(value = "/id/{id}")
    @Operation(summary = "Delete Client", description = "Delete client by Id, only for Admin and Master", tags = {"DELETE"})
    @PreAuthorize("hasAnyRole('ADMIN', 'MASTER')")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

}
