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
import spring.boot.expert.curso.dto.ClientProfileDTO;
import spring.boot.expert.curso.model.Client;
import spring.boot.expert.curso.model.Profile;
import spring.boot.expert.curso.repository.ClientRepository;
import spring.boot.expert.curso.repository.ProfileRepository;
import spring.boot.expert.curso.service.exception.DatabaseException;
import spring.boot.expert.curso.service.exception.ExceptionBusinessRules;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    Client client;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ProfileRepository profileRepository;

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

        Profile profile = profileRepository.findById(3).get();
        client.setProfile(profile);

        client = clientRepository.save(client);
        return new ClientDTO(client.getId(), client.getName(), client.getCpf(), client.getEmail(), client.getPhone(),
                client.getProfile().getName());
    }

    @Transactional
    public void update(ClientDTO dto) {
        searchClient();
        Integer idClient = client.getId();

        clientRepository.updateClient(idClient, dto.getName(), dto.getCpf(), dto.getPhone());
    }

    @Transactional
    public ClientDTO updateMaster(Integer id, ClientProfileDTO dto) {

        clientRepository.updateProfileId(id, dto.getProfileId());

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ExceptionBusinessRules("Client not found, id does not exist: " + id));
        return new ClientDTO(client.getId(), client.getName(), client.getCpf(), client.getEmail(), client.getPhone(),
                client.getProfile().getName());
    }

    @Transactional
    public void delete(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ExceptionBusinessRules("Client not found, id does not exist: " + id));
        if (client.getOrders().size() > 0) {
            throw new DatabaseException("Client has orders and cannot be deleted");
        }
        clientRepository.delete(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> list = clientRepository.findAll(pageable);
        return list.map(x -> new ClientDTO(x.getId(), x.getName(), x.getCpf(), x.getEmail(), x.getPhone(),
                x.getProfile().getName()));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ExceptionBusinessRules("Client not found, id does not exist: " + id));
        return new ClientDTO(client.getId(), client.getName(), client.getCpf(), client.getEmail(), client.getPhone(),
                client.getProfile().getName());
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findByNameLike(Pageable pageable, String name) {
        Page<Client> list = clientRepository.findByNameLikeIgnoreCase(pageable, "%" + name + "%");
        return list.map(x -> new ClientDTO(x.getId(), x.getName(), x.getCpf(), x.getEmail(), x.getPhone(),
                x.getProfile().getName()));
    }

    private void searchClient() {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)) {
            String uselog = autenticado.getName();
            client = clientRepository.findByEmail(uselog);
        }
    }

}
