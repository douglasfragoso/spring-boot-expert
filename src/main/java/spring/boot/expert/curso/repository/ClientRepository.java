package spring.boot.expert.curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.expert.curso.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

    List<Client> findByNomeLike(String nome);

    boolean existsByNome(String nome);
}
