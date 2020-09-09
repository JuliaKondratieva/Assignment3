package com.labs.a1;

import java.time.LocalDate;
import java.util.*;

public class Service {
    private final DataServer data;

    public Service(){
        data=new DataServer();
    }

    public DataServer getData(){
        return data;
    }

    public synchronized void addToList(Book mapBook, User user, HashMap<Book, Queue<User>> hashList) {
        Queue<User> usersList = hashList.get(mapBook);

        if(usersList == null) {
            usersList = new LinkedList<>();
            usersList.add(user);
            hashList.put(mapBook, usersList);
        } else if(!usersList.contains(user))
            usersList.add(user);
    }

    public void acceptOrder(Order order){
        Book book = order.getBook();
        book.isNotAvailable();
        Queue<Order> ordersArray = data.getOrdersArray();
        ordersArray.add(order);
        HashMap<Book, User> belongList = data.getBelongList();
        belongList.put(book, order.getUser());
    }

    public void addToWaitList(Order order){
        addToList(order.getBook(), order.getUser(), data.getWaitingList());
    }

    public void orderSubmit(Order order) {
        if (order.getBook().getAvailability() && !order.getBook().getRepair()) {
            acceptOrder(order);
        } else if (!order.getBook().getAvailability()) {
            addToWaitList(order);
        }
    }

    public void orderClean(Order order){
        data.getOrdersArray().remove(order);
    }

    public void moveQueue(HashMap<Book, Queue<User>> waitingList){
        for(Book book : waitingList.keySet()){
            Queue<User> userByBook = waitingList.get(book);

            if(book.getAvailability() && userByBook.peek()!=null) {
                Order newOrder = new Order(book, userByBook.poll(), LocalDate.now(), LocalDate.now().plusWeeks(3), false);
                orderSubmit(newOrder);
            }
        }
    }
}



