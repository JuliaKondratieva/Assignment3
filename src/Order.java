import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Order {
    private Book book;
    private User user;
    private LocalDate fromDate;
    private LocalDate dueDate;
    Order()
    {
        book=null;
        user=null;
        fromDate=null;
        dueDate=null;
    }
    Order(Book book, User user, LocalDate fromDate, LocalDate dueDate){
        this();
        this.book=book;
        this.user=user;
        this.fromDate=fromDate;
        this.dueDate=dueDate;
    }
    public Book getBook(){
        return book;
    }
    public User getUser(){
        return user;
    }
    public LocalDate getDueDate(){
        return dueDate;
    }
    public LocalDate getFromDate(){
        return fromDate;
    }
}
