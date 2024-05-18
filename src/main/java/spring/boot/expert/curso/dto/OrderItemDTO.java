package spring.boot.expert.curso.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Represents a item in the order")
public class OrderItemDTO  implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Unique identifier of the product", example = "1", required = true)
    private Integer product;

    @Schema(description = "Quantity of the product", example = "1", required = true)
    private Integer quantity;
    
}

