package spring.boot.expert.curso.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Represents response of login in the system")
public class ClientLoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the person", example = "1", accessMode = AccessMode.READ_ONLY)
    private Integer id;

    @Schema(description = "Name of the person", example = "John Doe", accessMode = AccessMode.READ_ONLY)
    private String name;

    @Schema(description = "Token of the client", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzcHJpbmdib290ZXhwZXJ0LXJlc3QtYXBpIiwic3ViIjoibWFyaWFAZW1haWwuY29tIiwiZXhwIjoxNzE1NTYyMzMzLCJpZCI6Mn0.n6MGRz7MRd4-EvWrBX1mclqOfzmf4fF3enhVz8sZHPo"
            , accessMode = AccessMode.READ_ONLY)
    private String token;

}
