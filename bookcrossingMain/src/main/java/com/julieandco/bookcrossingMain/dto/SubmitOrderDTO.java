package com.julieandco.bookcrossingMain.dto;

import com.julieandco.bookcrossingMain.entity.Book;
import com.julieandco.bookcrossingMain.entity.User;

public class SubmitOrderDTO {
    private User user;
    private Book book;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
