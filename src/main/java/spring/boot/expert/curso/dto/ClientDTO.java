package spring.boot.expert.curso.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private String profile;


    public ClientDTO(Integer id, String name, String cpf, String email, String phone, String profile) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
        this.profile = profile;
    }

}
