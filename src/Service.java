import java.util.ArrayList;
import java.util.HashMap;

public class Service {
    private ArrayList<Book> books;
    private HashMap<Book, ArrayList<User>> waitingList;
    private HashMap<Book, User> belongList;
    private ArrayList<Order> orders;

    public ArrayList<Book> sortGenre(ArrayList<Book> books, Genre genre) {
        ArrayList<Book> sorted = new ArrayList<Book>();
        for (Book book : books)
            if (book.getGenre().equals(genre)) {
                sorted.add(book);
            }

        return sorted;
    }

    public ArrayList<Book> sortRating(ArrayList<Book> books, int rating) {
        ArrayList<Book> sorted = new ArrayList<Book>();
        for (Book book : books)
            if (book.getRating() == rating) {
                sorted.add(book);
            }

        return sorted;
    }

    public ArrayList<Book> sortAuthor(ArrayList<Book> books, String author) {
        ArrayList<Book> sorted = new ArrayList<Book>();
        for (Book book : books)
            if (book.getAuthor().equals(author)) {
                sorted.add(book);
            }

        return sorted;
    }

    public ArrayList<Book> sortYear(ArrayList<Book> books, long year) {
        ArrayList<Book> sorted = new ArrayList<Book>();
        for (Book book : books)
            if (book.getYear() == year) {
                sorted.add(book);
            }

        return sorted;
    }

    public void orderSubmit(Order order) {
        if (order.getBook().getAvailability() && !order.getBook().getRepair()) {
            belongList.put(order.getBook(), order.getUser());
            order.getBook().setAvailability(false);
        } else if (!order.getBook().getAvailability()) {
            addToList(order.getBook(), order.getUser(), waitingList);
        } else
            order.getBook().setRepair(true);
    }
}

    synchronized void addToList(Book mapBook, User user, HashMap<Book, ArrayList<User>> hashList) {
        ArrayList<User> usersList = hashList.get(mapBook);

        // if list does not exist create it
        if(usersList == null) {
            usersList = new ArrayList<User>();
            usersList.add(user);
            hashList.put(mapBook, usersList);
        } else {
            // add if item is not already in list
            if(!usersList.contains(user))
                usersList.add(user);
        }
    }
}