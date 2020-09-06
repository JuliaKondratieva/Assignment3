package com.labs.a1;

import java.time.LocalDate;

public class Order {
    private Book book;
    private User user;
    private LocalDate fromDate;
    private LocalDate dueDate;
    private boolean isDelivered;
    Order()
    {
        book=new Book();
        user=new User();
        fromDate=null;
        dueDate=null;
        isDelivered=false;
    }
    Order(Book book, User user, LocalDate fromDate, LocalDate dueDate, boolean isDelivered){
        this();
        this.book=book;
        this.user=user;
        this.fromDate=fromDate;
        this.dueDate=dueDate;
        this.isDelivered=isDelivered;
    }
    public Book getBook(){
        return book;
    }
    public User getUser(){
        return user;
    }
    public void setDelivered(boolean deliveryStatus){
        this.isDelivered=deliveryStatus;
    }
    public boolean getDelivered(){
        return isDelivered;
    }
    public LocalDate getDueDate(){
        return dueDate;
    }
    public LocalDate getFromDate(){
        return fromDate;
    }
}
