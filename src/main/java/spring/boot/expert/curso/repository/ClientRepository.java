package spring.boot.expert.curso.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.boot.expert.curso.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

    Page<Client> findByNameLike(Pageable pegeable, String name);

    Client findByEmail(String email);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    @Query("from Client where email =  ?1")
	public List<Client> searchClient(String email);
    
}
