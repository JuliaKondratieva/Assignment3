package com.labs.a1;

import java.util.ArrayList;

public class FilterService {
    DataServer dataServer=new DataServer();

    public ArrayList<Book> filterGenre(ArrayList<Book> books, Genre genre) {
        ArrayList<Book> sorted = new ArrayList<>();

        for (Book book : books)
            if (book.getGenre().equals(genre)) {
                sorted.add(book);
            }

        return sorted;
    }

    public ArrayList<Book> filterRating(ArrayList<Book> books, int rating) {
        ArrayList<Book> sorted = new ArrayList<>();

        for (Book book : books)
            if (book.getRating() == rating) {
                sorted.add(book);
            }

        return sorted;
    }

    public ArrayList<Book> filterYear(ArrayList<Book> books, long year) {
        ArrayList<Book> sorted = new ArrayList<>();

        for (Book book : books)
            if (book.getYear() == year) {
                sorted.add(book);
            }

        return sorted;
    }

    public Book findBookTitle(String inputTitle){
        Book ordered=new Book();
        ArrayList<Book> arrayBook = dataServer.getBooksArray();
        for(int i=0; i< arrayBook.size();++i){
            Book bookElement =arrayBook.get(i);
            String title = bookElement.getTitle();

            if(title.compareTo(inputTitle)==0)
                ordered=bookElement;
        }

        return ordered;
    }

    public void printArray(ArrayList<Book> array) {
        for (int i = 0; i < array.size(); i++) {
            Book element = array.get(i);
            element.showInfoOrder();
        }
    }
}
