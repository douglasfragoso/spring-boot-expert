package spring.boot.expert.curso.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import spring.boot.expert.curso.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Page<Client> findByNameLikeIgnoreCase(Pageable pegeable, String name);

    Client findByEmail(String email);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tb_client SET perfil_id = :profileId WHERE id = :clientId", nativeQuery = true)
    void updateProfileId(Integer clientId, Integer profileId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tb_client SET name = :name, cpf = :cpf, phone = :phone WHERE id = :id", nativeQuery = true)
    void updateClient(Integer id, String name, String cpf, String phone);

}
