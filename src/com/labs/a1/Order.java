package com.labs.a1;

import java.time.LocalDate;

public class Order {
    private Book book;
    private final User user;
    private LocalDate fromDate;
    private LocalDate dueDate;
    private boolean deliveryState;

    Order()
    {
        book=new Book();
        user=new User();
        deliveryState=false;
    }

    Order(Book book, User user, LocalDate fromDate, LocalDate dueDate, boolean isDelivered){
        this.book=book;
        this.user=user;
        this.fromDate=fromDate;
        this.dueDate=dueDate;
        this.deliveryState=isDelivered;
    }

    public Book getBook(){
        return book;
    }

    public User getUser(){
        return user;
    }

    public void isDelivered(){
        this.deliveryState=true;
    }

    public boolean getDeliveryState(){
        return deliveryState;
    }

    public LocalDate getDueDate(){
        return dueDate;
    }

    public String toString(){
        return "Book: " + book.getTitle() + "\r\n User: " + user.getUsername() + user.getIdNumber().toString() + "\r\n Order date: " + fromDate.toString() + "\r\n due Date: " + dueDate.toString();
    }

    public void showInfoOrder(){
        System.out.println(this.toString());
    }
}
