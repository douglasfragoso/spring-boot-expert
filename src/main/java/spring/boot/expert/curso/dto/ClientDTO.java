package spring.boot.expert.curso.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.boot.expert.curso.enums.Profile;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Represents a person in the system")
public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the person", example = "1", accessMode = AccessMode.READ_ONLY)
    private Integer id;
  
    @Schema(description = "Name of the person", example = "John Doe", required = true)
    private String name;
    
    @Schema(description = "CPF of the client", example = "12345678900", required = true)
    private String cpf;

    @Schema(description = "Email address of the client", example = "johndoe@example.com", required = true)
    private String email;

    @Schema(description = "Password of the client", example = "password123", required = true)
    private String userPassword;

    @Schema(description = "Phone number of the client", example = "81912345678", required = true)
    private String phone;

    @Schema(description = "Profile of the client", example = "USER", accessMode = AccessMode.READ_ONLY)
    private String profile;


    public ClientDTO(Integer id, String name, String cpf, String email, String phone, Profile profile) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
        this.profile = profile.name();
    }

}
