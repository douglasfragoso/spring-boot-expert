package spring.boot.expert.curso.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.boot.expert.curso.enums.OrderStatus;


@NoArgsConstructor
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private Integer client;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
    @Getter @Setter
    private Instant date;
    
    @Getter @Setter
    private BigDecimal total;

    @Getter @Setter
    private String status;

    @Getter 
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
