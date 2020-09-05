import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class DataServer {
    private ArrayList<Book> books;
    private HashMap<Book, Queue<User>> waitingList;
    private HashMap<Book, User> belongList;
    private Queue<Order> orders;

    public Queue<Order> getOrdersArray() {
        return orders;
    }
    public ArrayList<Book> getBooksArray() {
        return books;
    }
    public void setBooksArray(ArrayList<Book> books) {
        this.books = books;
    }
    public HashMap<Book, User> getBelongList() {
        return belongList;
    }
    public HashMap<Book, Queue<User>> getWaitingList() {
        return waitingList;
    }
}
