package com.julieandco.bookcrossing.book.entity.dto;

import com.julieandco.bookcrossing.book.entity.Book;

import java.util.List;

public class BooksDTO {
    private List<BookDTO> books;

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
