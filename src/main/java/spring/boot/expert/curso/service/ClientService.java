package spring.boot.expert.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.expert.curso.dto.ClientDTO;
import spring.boot.expert.curso.model.Client;
import spring.boot.expert.curso.repository.ClientRepository;
import spring.boot.expert.curso.service.exception.ExceptionBusinessRules;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client client = new Client();
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client = clientRepository.save(client);
        return new ClientDTO(client.getId(), client.getName(), client.getCpf(), client.getEmail(), client.getPhone());
    }

    @Transactional
    public ClientDTO update(Integer id, ClientDTO dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ExceptionBusinessRules("Client not found, id does not exist: " + id));
        ;
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client = clientRepository.save(client);
        return new ClientDTO(client.getId(), client.getName(), client.getCpf(), client.getEmail(), client.getPhone());
    }

    @Transactional
    public void delete(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ExceptionBusinessRules("Client not found, id does not exist: " + id));
        clientRepository.delete(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> list = clientRepository.findAll(pageable);
        return list.map(x -> new ClientDTO(x.getId(), x.getName(), x.getCpf(), x.getEmail(), x.getPhone()));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ExceptionBusinessRules("Client not found, id does not exist: " + id));
        return new ClientDTO(client.getId(), client.getName(), client.getCpf(), client.getEmail(), client.getPhone());
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findByNameLike(Pageable pageable, String name) {
        Page<Client> list = clientRepository.findByNameLike(pageable, "%" + name + "%");
        return list.map(x -> new ClientDTO(x.getId(), x.getName(), x.getCpf(), x.getEmail(), x.getPhone()));
    }

}
