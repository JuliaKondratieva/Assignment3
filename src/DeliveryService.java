import java.util.ArrayList;

public class DeliveryService {
    public void deliverToBox(Order order, Box box){
        box.addingBook(order.getBook());
    }

    public void deliverToUser(Order order, User user){
        order.getBook().setDelivered(true);
    }
}
