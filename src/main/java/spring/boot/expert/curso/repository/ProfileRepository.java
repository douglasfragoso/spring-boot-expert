package spring.boot.expert.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.expert.curso.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer>{
    
}
