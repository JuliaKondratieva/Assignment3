package com.labs.a1;

import java.util.*;

public class DataServer {
    private final ArrayList<Book> books;
    private final HashMap<Book, Queue<User>> waitingList;
    private final HashMap<Book, User> belongList;
    private final Queue<Order> orders;

    public DataServer(){
        books=new ArrayList<>();
        waitingList=new HashMap<>();
        belongList=new HashMap<>();
        orders=new LinkedList<>();
    }

    public Queue<Order> getOrdersArray() {
        return orders;
    }

    public ArrayList<Book> getBooksArray() {
        return books;
    }

    public HashMap<Book, User> getBelongList() {
        return belongList;
    }

    public HashMap<Book, Queue<User>> getWaitingList() {
        return waitingList;
    }

    public void addBook(Book book){
        getBooksArray().add(book);
    }

    public void ordersOutput(){
        Queue<Order> cloneQueue = orders;
        Order checkOrder, getOrder;

        for (int i = 0; i < cloneQueue.size(); ++i) {
            checkOrder=cloneQueue.peek();

            if(checkOrder.getDeliveryState())
                continue;

            getOrder=cloneQueue.poll();
            getOrder.showInfoOrder();
            orders.add(getOrder);

        }
    }

    public ArrayList<String> belongListToStringArray() {
        ArrayList<String> infoArray=new ArrayList<>();
        Set<Book> bookSet = belongList.keySet();
        ArrayList<Book> keysBook = new ArrayList<>(bookSet);

        for(int i=0; i<keysBook.size();++i){
            Book keyBook = keysBook.get(i);
            User user = belongList.get(keyBook);
            infoArray.add("Book: " + keyBook.getTitle() + "-- User: " + user.getUsername());
        }

        return infoArray;
    }

    public void infoBelongList(){
        for(int i=0; i<belongListToStringArray().size();++i)
            System.out.println(belongListToStringArray());
    }
}
