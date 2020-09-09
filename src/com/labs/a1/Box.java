package com.labs.a1;

import java.util.ArrayList;

public class Box {
    private final ArrayList<Book> books;
    private String address;

    Box() {
        books=new ArrayList<>();
        address="";
    }

    public ArrayList<Book> getArrayBook() {
        return books;
    }

    public void setAddress(String address){
        this.address=address;
    }

    public String getAddress() {
        return address;
    }
}
