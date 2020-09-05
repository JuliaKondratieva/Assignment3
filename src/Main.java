import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FilterService filterService=new FilterService();
        Book b1, b2, b3;
        ArrayList<Book> books=new ArrayList<Book>();
        b1=new Book("11/22/63", "Stephen King", 2011, 8, Genre.FANTASY, true, false, false);
        b2=new Book("The Wayward Pines", "Blake Crouch", 2012, 7, Genre.THRILLER, true, false, false);
        b3=new Book("The Three-Body Problem", "Liu Cixin", 2008, 7.5, Genre.SCIENCEFICTION, true, false, false);
        books.add(b1);
        books.add(b2);
        books.add(b3);

        User user;
        System.out.println("Hello! Enter your name: ");
        String name = System.console().readLine();
        System.out.println("Hello! Enter your address: ");
        String address = System.console().readLine();
        int id=11223;
        user=new User(name, id, address);

        System.out.println("-Congrats! You've been logged in-");

        System.out.println("----CATALOGUE----");
        filterService.printArray(books);
        System.out.println("----Filter catalogue by genre:----");
        System.out.println("----Science fiction:----");
        ArrayList<Book> filtered = new ArrayList<Book>();
        filtered = filterService.filterGenre(books, Genre.SCIENCEFICTION);
        filterService.printArray(filtered);
        System.out.println("----Filter catalogue by year:----");
        System.out.println("----2012:----");
        filtered = filterService.filterYear(books, 2012);
        filterService.printArray(filtered);
        System.out.println("----Filter catalogue by rating:----");
        System.out.println("----8:----");
        filtered = filterService.filterRating(books, 8);
        filterService.printArray(filtered);
        System.out.println("");
        System.out.println("");
        System.out.println("------------Ordering a book---------");
        System.out.println("Enter the title of the book");
        String titleOrder=System.console().readLine();
        Book ordered=new Book();
        for(int i=0; i< books.size();++i){
            if(books.get(i).getTitle().compareTo(titleOrder)==0)
                    ordered=books.get(i);
        }
        Service orderService=new Service();
        Order order=new Order(ordered, user, LocalDate.now(), LocalDate.now().plusWeeks(3));
        orderService.orderSubmit(order);
        String addressBox="Kreshatyk";
        Box box=new Box(null, addressBox);
        BoxService boxService=new BoxService();
        DeliveryService deliveryService=new DeliveryService();
        deliveryService.deliverToBox(order, box);

        System.out.println("---Info about your order---");
        orderService.outputOrder(order);


        System.out.println("Your order is delivered to box");

        System.out.println("INFO FOR MANAGER: List of the books in the box");
        filterService.printArray(box.getArrayBook());

        boxService.checkOut(box, order);
        System.out.println("You received your order! Enjoy!");
        System.out.println("UPD INFO FOR MANAGER: List of the books in the box");
        filterService.printArray(box.getArrayBook());

    }
}
