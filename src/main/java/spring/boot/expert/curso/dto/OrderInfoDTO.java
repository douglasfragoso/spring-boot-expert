package spring.boot.expert.curso.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.boot.expert.curso.enums.OrderStatus;


@NoArgsConstructor
public class OrderInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private Integer clientID;

    @Getter @Setter
    private String clientName;

    @Getter @Setter
    private Instant date;

    @Getter @Setter
    private BigDecimal total;

    @Getter @Setter
    private String status;

    @Getter 
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderInfoDTO(Integer id, Integer clientID, String clientName, Instant date, BigDecimal total, OrderStatus status, List<OrderItemDTO> items) {
        this.id = id;
        this.clientID = clientID;
        this.clientName = clientName;
        this.date = date;
        this.total = total.setScale(2, RoundingMode.HALF_UP);
        this.status = status.name();
        this.items = items;
    }

}
