package com.labs.a1;

import java.util.ArrayList;
import java.util.Queue;

public class BoxService {
    private final ArrayList<Box> boxes=new ArrayList<>();
    private Service service;

    public void checkOut(Box box, Order order){
        order.isDelivered();
        ArrayList<Book> books = box.getArrayBook();
        books.remove(order.getBook());
        service.orderClean(order);
    }

    public void receive(Box box, Book book){
        DataServer data = service.getData();
        Queue<Order> array = data.getOrdersArray();
        Order arrayElement = array.element();
        Book bookElement = arrayElement.getBook();
        String title = bookElement.getTitle();

        if(!title.equals(book.getTitle()))
            book.isAvailable();

        ArrayList<Book> arrayBook=box.getArrayBook();
        arrayBook.add(book);
        service.moveQueue(data.getWaitingList());
    }

    public ArrayList<Box> addBox(Box box){
        boxes.add(box);

        return boxes;
    }

    public void setService(Service service){
        this.service=service;
    }
}
