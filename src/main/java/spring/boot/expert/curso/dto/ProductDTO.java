package spring.boot.expert.curso.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Schema(description = "Represents a product in the system")
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the product", example = "1", accessMode = AccessMode.READ_ONLY)
    private Integer id;

    @Schema(description = "Name of the product", example = "Product Name", required = true)
    private String name;

    @Schema(description = "Description of the product", example = "Product Description", required = true)
    private String description;

    @Schema(description = "Price of the product", example = "10.00", required = true)
    private BigDecimal price;

    public ProductDTO(Integer id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price.setScale(2, RoundingMode.HALF_UP); // Arredonda o preço para duas casas decimais
    }

}
