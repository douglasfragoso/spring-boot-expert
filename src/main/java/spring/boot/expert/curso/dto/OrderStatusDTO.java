package spring.boot.expert.curso.dto;


import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@Schema(description = "Represents the status of the order")
public class OrderStatusDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    @Schema(description = "Status of the order", example = "1", required = true)
    private Integer status;
}
