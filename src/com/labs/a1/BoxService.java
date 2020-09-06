package com.labs.a1;

import java.util.ArrayList;

public class BoxService {
    private ArrayList<Box> boxes;

    public void checkOut(Box box, Order order){
        order.setDelivered(true);
        box.getArrayBook().remove(order.getBook());
    }
    public void receive(Box box, Book book){
        book.setAvailability(true);
        box.getArrayBook().add(book);
    }
}
