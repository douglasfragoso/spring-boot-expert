package spring.boot.expert.curso.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import spring.boot.expert.curso.enums.Profile;

@EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tb_client")
public class Client implements UserDetails {

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
    @Column(name = "cpf", length = 11, unique = true, updatable = false)
    @NotBlank(message = "The field cpf is required")
    @CPF(message = "The CPF is invalid")
    private String cpf;

    @Getter @Setter
    @Column(name = "email", unique = true, updatable = false)
    @NotBlank(message = "The field email is required")
    @Email(message = "The email is invalid")
    private String email;

    @Setter
    @Column(name = "password", length = 100)
    @NotBlank(message = "The field password is required")
    private String userPassword;

    @Getter @Setter
    @JsonFormat(pattern = "\\(d{2})-\\d{4,5}.-\\{4}")
    @Column(name = "phone", length = 11, unique = true, updatable = false)
    @NotBlank(message = "The field phone is required")
    private String phone;

    @JoinColumn(name = "profile")
    private Integer profile; 

    @Getter 
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    public Client(Integer id, String name, String cpf, String email, String userPassword, String phone) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.userPassword = userPassword;
        this.phone = phone;
    }

    public Profile getProfile() {
        return Profile.valueOf(profile);
    }

    public void setProfile(Profile profile) {
        if(profile != null){
            this.profile = profile.getCode();
    }

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (profile.equals(3)){
            return List.of(new SimpleGrantedAuthority("ROLE_MASTER"));
        } else if (profile.equals(1)) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (profile.equals(2)) {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_GUEST"));
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
       return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
