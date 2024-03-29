package spring.boot.expert.curso.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.expert.curso.dto.ClientDTO;
import spring.boot.expert.curso.model.Client;
import spring.boot.expert.curso.repository.ClientRepository;

@Service
public class ClientService {
   
        @Autowired
        private ClientRepository clientRepository;

        @Transactional
        public ClientDTO insert(ClientDTO dto){
            Client client = new Client();
            client.setName(dto.getName());
            client = clientRepository.save(client);
            return new ClientDTO(client.getId(), client.getName());
        }

        @Transactional
        public ClientDTO update(Integer id, ClientDTO dto){
            Client client = clientRepository.findById(id).get();
            client.setName(dto.getName());
            client = clientRepository.save(client);
            return new ClientDTO(client.getId(), client.getName());
        }
        
        @Transactional
        public void delete(Integer id){
            clientRepository.deleteById(id);
        }

        @Transactional(readOnly = true)
        public List<ClientDTO> findAll(){
            List<Client> list = clientRepository.findAll();
            return list.stream().map(x -> new ClientDTO(x.getId(), x.getName())).toList(); 
        }

        @Transactional(readOnly = true)
        public ClientDTO findById(Integer id){
            Client client = clientRepository.findById(id).get();
            return new ClientDTO(client.getId(), client.getName());
        }

        // @Transactional(readOnly = true)
        // public List<ClientDTO> findByName(String name){
        //     if(!clientRepository.existsByName(name)){
        //         return null;
        //     }
        //     List<Client> list = clientRepository.findByNameLike("%"+ name + "%");
        //     return list.stream().map(x -> new ClientDTO(x.getId(), x.getName())).toList();
        // }
        
    }   

   
 
    
