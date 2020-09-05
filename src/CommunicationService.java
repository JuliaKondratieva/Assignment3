import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CommunicationService {

    public ArrayList<Order> checkingDate(ArrayList<Order> orders) {
        ArrayList<Order> overdueOrders=new ArrayList<Order>();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
       // System.out.println(date.format(formatter));
        for(Order order:orders) {
            if (order.getDueDate().compareTo(date) > 0) {
                overdueOrders.add(order);
            }
        }
        return overdueOrders;
    }
}
