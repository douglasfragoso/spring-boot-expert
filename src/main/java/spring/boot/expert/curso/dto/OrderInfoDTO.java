package spring.boot.expert.curso.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class OrderInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer clientID;
    private String clientName;
    private Instant date;
    private BigDecimal total;
    private List<OrderItemDTO> items = new ArrayList<>();

    public OrderInfoDTO(Integer id, Integer clientID, String clientName, Instant date, BigDecimal total, List<OrderItemDTO> items) {
        this.id = id;
        this.clientID = clientID;
        this.clientName = clientName;
        this.date = date;
        this.total = total.setScale(2, RoundingMode.HALF_UP);
        this.items = items;
    }

}
