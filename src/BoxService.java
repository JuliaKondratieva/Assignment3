import java.util.ArrayList;

public class BoxService {
    private ArrayList<Box> boxes;

    public void checkOut(Box box, Order order){
        order.getBook().setDelivered(true);
        box.extradition(order.getBook());
    }
    public void receive(Box box, Book book){
        book.setAvailability(true);
        book.setDelivered(false);
        box.addingBook(book);
    }
}
