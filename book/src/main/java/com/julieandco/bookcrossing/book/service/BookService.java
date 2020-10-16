package com.julieandco.bookcrossing.book.service;

import com.julieandco.bookcrossing.book.entity.Book;
import com.julieandco.bookcrossing.book.entity.dto.BookDTO;
import com.julieandco.bookcrossing.book.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepository;

    @Autowired
    public BookService(BookRepo bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    @Transactional
    public Book findByTitle(String title){
        return bookRepository.findBookByTitle(title);
    }

    @Transactional
    public void addBook(Book book){
        if(bookRepository.findBookByTitle(book.getTitle()) == null){
            bookRepository.save(book);
        }
    }
}
