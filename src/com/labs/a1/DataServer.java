package com.labs.a1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class DataServer {
    private ArrayList<Book> books;
    private HashMap<Book, Queue<User>> waitingList;
    private HashMap<Book, User> belongList;
    private Queue<Order> orders;

    public DataServer(){
        books=new ArrayList<Book>();
        waitingList=new HashMap<Book, Queue<User>>();
        belongList=new HashMap<Book, User>();
        orders=new LinkedList<Order>();
    }

    public Queue<Order> getOrdersArray() {
        return orders;
    }
    public ArrayList<Book> getBooksArray() {
        return books;
    }
    public void setBooksArray(ArrayList<Book> books) {
        this.books = books;
    }
    public HashMap<Book, User> getBelongList() {
        return belongList;
    }
    public HashMap<Book, Queue<User>> getWaitingList() {
        return waitingList;
    }

    public ArrayList<Book> addBook(Book book){
        getBooksArray().add(book);
        return books;
    }
}
