package spring.boot.expert.curso.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.expert.curso.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

    Page<Client> findByNameLike(Pageable pegeable, String name);

    boolean existsByName(String name);
    
}
