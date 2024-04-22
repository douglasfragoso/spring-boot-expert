package spring.boot.expert.curso.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "tb_product" )
public class Product implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 100, unique = true)
    @NotEmpty(message = "The field name is required")
    private String name;

    @Column(name = "description", length = 300)
    @NotEmpty(message = "The field description is required")
    private String description;

    @Column(name = "price", precision = 1000, scale = 2)//precision é o tamanho do campo e scale é a quantidade de casas decimais
    @DecimalMin(value = "0.01", message = "The minimum value for the field price is 0.01")
    private BigDecimal price;
    

}
