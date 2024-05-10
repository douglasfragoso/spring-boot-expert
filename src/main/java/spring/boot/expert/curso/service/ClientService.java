package spring.boot.expert.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.expert.curso.dto.ClientDTO;
import spring.boot.expert.curso.model.Client;
import spring.boot.expert.curso.model.Profile;
import spring.boot.expert.curso.repository.ClientRepository;
import spring.boot.expert.curso.service.exception.DatabaseException;
import spring.boot.expert.curso.service.exception.ExceptionBusinessRules;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    Client client;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        if (clientRepository.existsByCpf(dto.getCpf())) {
            throw new DatabaseException("CPF already registered");
        }
        if (clientRepository.existsByEmail(dto.getEmail())) {
            throw new DatabaseException("Email already registered");
        }
        if (clientRepository.existsByPhone(dto.getPhone())) {
            throw new DatabaseException("Phone already registered");
        }
        Client client = new Client();
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setEmail(dto.getEmail());

        String codePassaword = passwordEncoder.encode(dto.getUserPassword());
        client.setUserPassword(codePassaword);
        
        client.setPhone(dto.getPhone());
        client.setProfile(new Profile(2));
        client = clientRepository.save(client);
        return new ClientDTO(client.getId(), client.getName(), client.getCpf(), client.getEmail(), client.getPhone(), client.getProfile());
    }

    @Transactional
    public ClientDTO update(ClientDTO dto) {
        searchClient();
        Integer idClient = client.getId();

        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ExceptionBusinessRules("Client not found, id does not exist: "));
        ;
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client = clientRepository.save(client);
        return new ClientDTO(client.getId(), client.getName(), client.getCpf(), client.getEmail(), client.getPhone(), client.getProfile());
    }

    @Transactional
    public void delete(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ExceptionBusinessRules("Client not found, id does not exist: " + id));
        if (client.getOrders().size() > 0){
            throw new DatabaseException("Client has orders and cannot be deleted");
        }
        clientRepository.delete(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> list = clientRepository.findAll(pageable);
        return list.map(x -> new ClientDTO(x.getId(), x.getName(), x.getCpf(), x.getEmail(), x.getPhone(), x.getProfile()));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ExceptionBusinessRules("Client not found, id does not exist: " + id));
        return new ClientDTO(client.getId(), client.getName(), client.getCpf(), client.getEmail(), client.getPhone(), client.getProfile());
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findByNameLike(Pageable pageable, String name) {
        Page<Client> list = clientRepository.findByNameLike(pageable, "%" + name + "%");
        return list.map(x -> new ClientDTO(x.getId(), x.getName(), x.getCpf(), x.getEmail(), x.getPhone(), x.getProfile()));
    }

    private void searchClient() {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if(!(autenticado instanceof AnonymousAuthenticationToken)) {
			String uselog = autenticado.getName();
			client = clientRepository.searchClient(uselog).get(0);
		}
	}

}
