package spring.boot.expert.curso.repository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.expert.curso.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

    // List<Client> findByNameLike(String nome);

    // boolean existsByName(String nome);
}
