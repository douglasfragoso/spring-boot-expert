package spring.boot.expert.curso.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.boot.expert.curso.model.Profile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String cpf;
    private String email;
    private String userPassword;
    private String phone;
    private Profile profile;
    private String token;

    public ClientDTO(Integer id, String name, String cpf, String email, String phone, Profile profile) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
        this.profile = profile;
    }

    public ClientDTO(Integer id, String name, Profile profile, String token) {
        this.id = id;
        this.name = name;
        this.profile = profile;
        this.token = token;
    }

}
