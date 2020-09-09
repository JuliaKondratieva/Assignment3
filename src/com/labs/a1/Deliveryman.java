package com.labs.a1;

import java.util.UUID;

public class Deliveryman {
    private final UUID idNumber;

    public Deliveryman(){
        idNumber= UUID.randomUUID();
    }

    public String toString(){
        return "Deliveryman: " + idNumber.toString();
    }

    public void showInfo(){
        System.out.println(this.toString());
    }
}
