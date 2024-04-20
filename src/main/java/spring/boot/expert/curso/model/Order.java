package spring.boot.expert.curso.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import spring.boot.expert.curso.enums.OrderStatus;

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

    @Getter 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
   
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Getter @Setter
    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT") 
    private Instant date;

    @Getter 
    @Column(name = "total", precision = 100, scale = 2)
    private BigDecimal total;

    @Column(name = "status")
    private Integer status;

    @Getter
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();
    
    public Order(Integer id, Client client, Instant date, BigDecimal total, OrderStatus status, List<OrderItem> items) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.total = total;
        setStatus(status);
        addItems(items);
    }

    public void addItems(List<OrderItem> newItems) {
        for (OrderItem newItem : newItems) {
            items.add(newItem);
        }
    }

    public void setTotal(List<OrderItem> items) {
        total = BigDecimal.ZERO;
        for (OrderItem item : items) {
            total = total.add(item.getSubTotal());
        }
    }

    public OrderStatus getStatus() {
        return OrderStatus.valueOf(status);
    }

    public void setStatus(OrderStatus status) {
        if(status != null){
            this.status = status.getCode();
        }
    }
}
