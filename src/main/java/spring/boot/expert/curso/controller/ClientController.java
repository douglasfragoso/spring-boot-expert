package spring.boot.expert.curso.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.expert.curso.dto.ClientDTO;
import spring.boot.expert.curso.service.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll(pageable));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findById(id));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Page<ClientDTO>> findByNameLike(Pageable pageable, @PathVariable("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findByNameLike(pageable, name));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.insert(dto));
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable("id") Integer id, @RequestBody ClientDTO dto) {
        return ResponseEntity.status(HttpStatus.FOUND).body(clientService.update(id, dto));
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

}
