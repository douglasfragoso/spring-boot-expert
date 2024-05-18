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
@Schema(description = "Represents a item in the order")
public class OrderItemInfoDTO  implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Unique identifier of the product", example = "1", accessMode = AccessMode.READ_ONLY)
    private Integer product;

    @Schema(description = "Name of the product", example = "Product Name", accessMode = AccessMode.READ_ONLY)
    private String productName;

    @Schema(description = "Quantity of the product", example = "1", accessMode = AccessMode.READ_ONLY)
    private Integer quantity;
    
}

