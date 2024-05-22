package spring.boot.expert.curso.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.boot.expert.curso.enums.OrderStatus;


@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents an order in the system")
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    @Schema(description = "Unique identifier of the order", example = "1", accessMode = AccessMode.READ_ONLY)
    private Integer id;

    @Getter @Setter
    @Schema(description = "Unique identifier of the person", example = "1", accessMode = AccessMode.READ_ONLY)
    private Integer client;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
    @Getter @Setter
    @Schema(description = "Date of the order", example = "2021-09-01T00:00:00Z", accessMode = AccessMode.READ_ONLY)
    private Instant date;
    
    @Getter @Setter
    @Schema(description = "Total value of the order", example = "100.00", accessMode = AccessMode.READ_ONLY)
    private BigDecimal total;

    @Getter @Setter
    @Schema(description = "Status of the order", example = "DELIVERED", accessMode = AccessMode.READ_ONLY)
    private String status;

    @Getter 
    @Schema(description = "Items of the order", required = true)
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderDTO(Integer id, Integer client, Instant date, BigDecimal total, OrderStatus status, List<OrderItemDTO> items) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.total = total.setScale(2, RoundingMode.HALF_UP);
        this.status = status.name();
        this.items = items;
    }

}
