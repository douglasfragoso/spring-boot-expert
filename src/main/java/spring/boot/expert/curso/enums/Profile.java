package spring.boot.expert.curso.enums;

public enum Profile {
    
    ADMIN(1),
    USER(2), 
    MASTER(3);
    
    private int code; 

    private Profile(int code) { 
        this.code = code;
    }	

    public int getCode() {
        return code;
    }

    public static Profile valueOf(int code) { 
        for (Profile value : Profile.values()) { 
            if (value.getCode() == code) { 
                return value; 
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code"); 
    }
}

