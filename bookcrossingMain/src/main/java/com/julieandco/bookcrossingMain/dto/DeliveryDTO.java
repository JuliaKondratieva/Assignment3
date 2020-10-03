package com.julieandco.bookcrossingMain.dto;

import com.julieandco.bookcrossingMain.entity.Book;
import com.julieandco.bookcrossingMain.entity.Box;

public class DeliveryDTO {

    private Box box;
    private Book book;

    public DeliveryDTO(){

    }

    public DeliveryDTO(Book book, Box box) {
        this.box= box;
        this.book = book;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Box getBox() {
        return box;
    }
}
