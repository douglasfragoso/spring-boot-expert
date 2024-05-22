package spring.boot.expert.curso.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a profile in the system")
public class ClientProfileDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Unique identifier of the profile", example = "1", required = true)
    private Integer profileId;

}
