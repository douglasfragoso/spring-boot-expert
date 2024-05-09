package spring.boot.expert.curso.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationDTO {

    @NotBlank(message = "The field email is required")
    private String email;

    @NotBlank(message = "The field password is required")
    private String password;
    
}
