import java.time.LocalDate;
import java.util.*;

public class Service {
    private DataServer data;

    public synchronized void addToList(Book mapBook, User user, HashMap<Book, Queue<User>> hashList) {
        Queue<User> usersList = hashList.get(mapBook);

        // if list does not exist create it
        if(usersList == null) {
            usersList = new LinkedList<>();
            usersList.add(user);
            hashList.put(mapBook, usersList);
        } else {
            // add if item is not already in list
            if(!usersList.contains(user))
                usersList.add(user);
        }
    }
    public void orderSubmit(Order order) {
        if (order.getBook().getAvailability() && !order.getBook().getRepair()) {
            //////////////CONSIDER ALREADY DONE IN DELIVERY belongList.put(order.getBook(), order.getUser());
            order.getBook().setAvailability(false);
            data.getOrdersArray().add(order);
            System.out.println("Your order is accepted");
        } else if (!order.getBook().getAvailability()) {
            addToList(order.getBook(), order.getUser(), data.getWaitingList());
            System.out.println("Your order is accepted but you're on the waitlist, expect your turn");
        } else {
            order.getBook().setRepair(true);
            System.out.println("Book is not available");
        }

    }

    public void orderClean(Order order){
        if(order.getBook().getDelivered()){
            data.getBelongList().put(order.getBook(), order.getUser());
            data.getOrdersArray().remove(order);
        }
    }

    //DEALING WITH WAITING LIST
    public void moveQueue(HashMap<Book, Queue<User>> waitingList){
        for(Book book : waitingList.keySet()){
            if(book.getAvailability()){
                waitingList.get(book).poll();
                Order newOrder=new Order(book, waitingList.get(book).element(), LocalDate.now(), LocalDate.now().plusWeeks(3));
                orderSubmit(newOrder);
                //belong to a new user, delete previous
            }
        }
    }
    public void outputOrder(Order order){
        System.out.println("Book: "+ order.getBook().getTitle());
        System.out.println("Order date: "+ order.getFromDate().toString());
        System.out.println("Expire date: "+ order.getDueDate().toString());

    }
    ///SERVICE IS DEALING WITH ORDERS


}



