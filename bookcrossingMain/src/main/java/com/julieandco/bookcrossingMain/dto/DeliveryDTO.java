package com.julieandco.bookcrossingMain.dto;

import com.julieandco.bookcrossingMain.entity.Book;
import com.julieandco.bookcrossingMain.entity.Bookorder;
import com.julieandco.bookcrossingMain.entity.Box;

public class DeliveryDTO {
    //private Bookorder bookorder;
    private Box box;
    private Book book;

    public DeliveryDTO(){

    }

    public DeliveryDTO(Book book, Box box) {
        //this.bookorder = bookorder;
        this.box= box;
        this.book = book;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    //public Bookorder getBookorder() {
      //  return bookorder;
    //}

    //public void setBookorder(Bookorder bookorder) {
      //  this.bookorder = bookorder;
    //}


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
