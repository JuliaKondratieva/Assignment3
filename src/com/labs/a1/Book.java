package com.labs.a1;

public class Book {
    private final String title;
    private final String author;
    private final long year;
    private final double rating;
    private Genre genre;
    private boolean available;
    private boolean needRepair;

    Book() {
        title="";
        author="";
        year=0;
        rating=0;
        available=true;
        needRepair=false;
    }

    Book(String title, String author, long year, double rating, Genre genre, boolean available, boolean needRepair){
        this.title=title;
        this.author=author;
        this.year=year;
        this.rating=rating;
        this.genre=genre;
        this.available=available;
        this.needRepair=needRepair;
    }

    public String getTitle(){
        return title;
    }

    public Genre getGenre(){
        return genre;
    }

    public double getRating(){
        return rating;
    }

    public long getYear(){
        return year;
    }

    public boolean getAvailability(){
        return available;
    }

    public boolean getRepair(){
        return needRepair;
    }

    public void setRepair(boolean need) {
        this.needRepair=need;
    }

    public void isAvailable() {
        this.available = true;
    }

    public void isNotAvailable() {
        this.available = false;
    }

    public String toString(){
        String bookString = "Book: " + title;
        String authorString = "\r\n Author: " + author;
        String genreString = "\r\n Genre: " + genre;
        String yearString = "\r\n Year: " + year;
        String ratingString = "\r\n Rating: " + rating;

        return  bookString + authorString + genreString + yearString + ratingString;
    }

    public void showInfoOrder() {
        System.out.println(this.toString());
    }
}
