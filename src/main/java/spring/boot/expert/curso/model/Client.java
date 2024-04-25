package spring.boot.expert.curso.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tb_client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Getter @Setter
    @Column(name = "name", length = 100)
    @NotBlank(message = "The field name is required")
    private String name;

    @Getter @Setter
    @Column(name = "cpf", length = 11, unique = true)
    @NotBlank(message = "The field cpf is required")
    @CPF(message = "The CPF is invalid")
    private String cpf;

    @Getter @Setter
    @Column(name = "email", unique = true)
    @NotBlank(message = "The field email is required")
    @Email(message = "The email is invalid")
    private String email;

    @Getter @Setter
    @JsonFormat(pattern = "\\(d{2})-\\d{4,5}.-\\{4}")
    @Column(name = "phone", length = 11, unique = true)
    @NotBlank(message = "The field phone is required")
    private String phone;

    @Getter 
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    public Client(Integer id, String name, String cpf, String email, String phone) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
    }

}
