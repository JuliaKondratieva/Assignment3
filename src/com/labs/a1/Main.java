package com.labs.a1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        FilterService filterService=new FilterService();
        //DataServer dataServer=new DataServer();
        Book b1, b2, b3;
        b1=new Book("11/22/63", "Stephen King", 2011, 8, Genre.FANTASY, true, false);
        b2=new Book("The Wayward Pines", "Blake Crouch", 2012, 7, Genre.THRILLER, true, false);
        b3=new Book("The Three-Body Problem", "Liu Cixin", 2008, 7.5, Genre.SCIENCEFICTION, true, false);
        filterService.dataServer.addBook(b1);
        filterService.dataServer.addBook(b2);
        filterService.dataServer.addBook(b3);

        User user;
        System.out.println("Hello! Enter your name: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();

        System.out.println("Enter your address: ");
        String address = reader.readLine();
        int id=11223;
        user=new User(name, id, address);

        System.out.println("-Congrats! You've been logged in-");

        System.out.println("----CATALOGUE----");
        filterService.printArray(filterService.dataServer.getBooksArray());
        System.out.println("----Filter catalogue by genre:----");
        System.out.println("----Science fiction:----");
        ArrayList<Book> filtered = new ArrayList<Book>();
        filtered = filterService.filterGenre(filterService.dataServer.getBooksArray(), Genre.SCIENCEFICTION);
        filterService.printArray(filtered);
        System.out.println("----Filter catalogue by year:----");
        System.out.println("----2012:----");
        filtered = filterService.filterYear(filterService.dataServer.getBooksArray(), 2012);
        filterService.printArray(filtered);
        System.out.println("----Filter catalogue by rating:----");
        System.out.println("----8:----");
        filtered = filterService.filterRating(filterService.dataServer.getBooksArray(), 8);
        filterService.printArray(filtered);
        System.out.println("");
        System.out.println("");
        System.out.println("------------Ordering a book 11/22/63---------");
        String titleOrder = "11/22/63";

       Book ordered=new Book();
       ordered=filterService.findBookTitle(titleOrder);

        Service orderService=new Service();
        Order order=new Order(ordered, user, LocalDate.now(), LocalDate.now().plusWeeks(3), false);
        orderService.orderSubmit(order);
        String addressBox="Kreshatyk";

        Box box=new Box(); ///gotta create a new array

        box.setAddress(addressBox);
        BoxService boxService=new BoxService();
        boxService.receive(box, b2);
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

        System.out.println("INFO FOR MANAGER: Book - User");
        orderService.outputbelongList(orderService.getData().getBelongList());

        System.out.println("----WHOOPS The Three-Body Problem book needs repair----");
        b3.setRepair(true);
        System.out.println("----Calling Repairman----");
        Repairman repairman= new Repairman();
        repairman.repair(b3);

    }
}
