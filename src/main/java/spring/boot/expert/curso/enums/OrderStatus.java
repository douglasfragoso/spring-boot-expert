package spring.boot.expert.curso.enums;


public enum OrderStatus {

    WAITING_PAYMENT(1),
    PAID(2),
    DELIVERED(3),
    CANCELED(4);

    private int code; 

    private OrderStatus(int code) { 
        this.code = code;
    }	

    public int getCode() {
        return code;
    }

    public static OrderStatus valueOf(int code) { // This method is used to convert an integer to an OrderStatus
        for (OrderStatus value : OrderStatus.values()) { // For each OrderStatus value in OrderStatus.values()
            if (value.getCode() == code) { // If the value.getCode() is equal to the code
                return value; // Return the value
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code"); 
    }
}

