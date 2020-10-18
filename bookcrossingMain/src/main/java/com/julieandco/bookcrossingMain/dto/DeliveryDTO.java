package com.julieandco.bookcrossingMain.dto;

public class DeliveryDTO {

    private BoxDTO box;
    private BookDTO book;

    public DeliveryDTO(){

    }

    public DeliveryDTO(BookDTO book, BoxDTO box) {
        this.box= box;
        this.book = book;
    }

    public void setBox(BoxDTO box) {
        this.box = box;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public BoxDTO getBox() {
        return box;
    }
}
