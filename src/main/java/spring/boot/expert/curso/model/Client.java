package spring.boot.expert.curso.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "The field name is required")
    private String name;

    @Getter @Setter
    @Column(name = "cpf", length = 11, unique = true)
    @NotEmpty(message = "The field cpf is required")
    private String cpf;

    @Getter @Setter
    @Column(name = "email", unique = true)
    @NotEmpty(message = "The field email is required")
    @Email(message = "The email is invalid")
    private String email;

    @Getter @Setter
    @Column(name = "phone", length = 11, unique = true)
    @NotEmpty(message = "The field phone is required")
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
