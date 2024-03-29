package spring.boot.expert.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<ClientDTO> findAll() {
        List<ClientDTO> result = clientService.findAll();
        return result;
    }

    @GetMapping(value = "/id/{id}")
    public ClientDTO findById(@PathVariable Integer id) {
        ClientDTO result = clientService.findById(id);
        return result;
    }

    // @GetMapping(value = "/name/{name}")
    // public List<ClientDTO> findByName(@PathVariable String name) {
    //     List<ClientDTO> result = clientService.findByName(name);
    //     return result;
    // }

}
