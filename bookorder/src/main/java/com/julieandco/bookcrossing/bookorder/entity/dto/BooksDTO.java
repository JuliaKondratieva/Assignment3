package com.julieandco.bookcrossing.bookorder.entity.dto;

import java.util.List;
import java.util.UUID;

public class BooksDTO {
    private List<BookDTO> books;

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
