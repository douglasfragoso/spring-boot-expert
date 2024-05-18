package spring.boot.expert.curso.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Represents a login in the system")
public class AuthenticationDTO {

    @NotBlank(message = "The field email is required")
    @Schema(description = "Email address of the client", example = "johndoe@example.com", required = true)
    private String email;

    @NotBlank(message = "The field password is required")
    @Schema(description = "Password of the client", example = "password123", required = true, accessMode = AccessMode.WRITE_ONLY)
    private String password;
    
}
