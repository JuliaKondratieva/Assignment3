package com.labs.a1;

import java.util.ArrayList;

public class Box {
    private ArrayList<Book> books;
    private String adress;

    Box()
    {
        books=new ArrayList<Book>();
        adress="";
    }
    Box(ArrayList<Book> books, String adress){
        //this();
        this.books=books;
        this.adress=adress;
    }
    public ArrayList<Book> getArrayBook() {
        return books;
    }
    public void setArrayBook(ArrayList<Book> books){
        this.books=books;
    }
    public void setAddress(String address){
        this.adress=address;
    }



    public String getAdress() {
        return adress;
    }
}
