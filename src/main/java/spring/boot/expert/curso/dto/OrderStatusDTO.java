package spring.boot.expert.curso.dto;


import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class OrderStatusDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    private Integer status;
}
