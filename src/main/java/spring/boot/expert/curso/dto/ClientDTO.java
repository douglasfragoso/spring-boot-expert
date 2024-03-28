package spring.boot.expert.curso.dto;

import java.io.Serializable;

public class ClientDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String name;

    public ClientDTO() {
    }
    
    public ClientDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nome) {
        this.name = nome;
    }

   
}
