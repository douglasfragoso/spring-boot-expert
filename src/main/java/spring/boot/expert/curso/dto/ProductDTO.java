package spring.boot.expert.curso.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;

    public ProductDTO() {
    }

    public ProductDTO(Integer id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price.setScale(2, RoundingMode.HALF_UP); // Arredonda o preço para duas casas decimais
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    

}
