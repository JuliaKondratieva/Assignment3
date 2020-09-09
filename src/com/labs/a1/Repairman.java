package com.labs.a1;

import java.util.UUID;

public class Repairman {

    private final UUID idNumber;

    public Repairman (){
        idNumber = UUID.randomUUID();
    }

    public UUID getIdNumber() {
        return idNumber;
    }

    public void repair(Book book) {
        book.isAvailable();
    }
}
