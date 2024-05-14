package spring.boot.expert.curso.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.boot.expert.curso.model.Profile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientLoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Profile profile;
    private String token;

}
