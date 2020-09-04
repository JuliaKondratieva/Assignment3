import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Order {
    private Book book;
    private User user;
    private Date fromDate;
    private Date dueDate;
    Order()
    {
        book=null;
        user=null;
        fromDate=null;
        dueDate=null;
    }
    Order(Book book, User user, Date fromDate, Date dueDate){
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
}
