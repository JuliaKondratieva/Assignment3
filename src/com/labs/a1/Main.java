package com.labs.a1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FilterService filterService=new FilterService();
        DataServer dataServer = filterService.dataServer;
        Service orderService=new Service();
        Box box=new Box();
        BoxService boxService=new BoxService();
        DeliveryService deliveryService=new DeliveryService();
        CommunicationService communicationService=new CommunicationService();
        Repairman repairman= new Repairman();
        ArrayList<Book> filtered;
        Book b1, b2, b3, ordered;

        b1=new Book("11/22/63", "Stephen King", 2011, 8, Genre.FANTASY, true, false);
        b2=new Book("The Wayward Pines", "Blake Crouch", 2012, 7, Genre.THRILLER, true, false);
        b3=new Book("The Three-Body Problem", "Liu Cixin", 2008, 7.5, Genre.SCIENCEFICTION, true, false);
        filterService.dataServer.addBook(b1);
        filterService.dataServer.addBook(b2);
        filterService.dataServer.addBook(b3);

        System.out.println("Hello! Enter your name: ");
        String name = reader.readLine();
        User user=new User();
        user.setUsername(name);
        System.out.println("-Congrats! You've been logged in-");

        System.out.println("\r\n----CATALOGUE----");
        filterService.printArray(dataServer.getBooksArray());
        System.out.println("----Filter catalogue by genre:----");
        System.out.println("----Science fiction:----");
        filtered = filterService.filterGenre(dataServer.getBooksArray(), Genre.SCIENCEFICTION);
        filterService.printArray(filtered);
        System.out.println("----Filter catalogue by year:----");
        System.out.println("----2012:----");
        filtered = filterService.filterYear(dataServer.getBooksArray(), 2012);
        filterService.printArray(filtered);
        System.out.println("----Filter catalogue by rating:----");
        System.out.println("----8:----");
        filtered = filterService.filterRating(dataServer.getBooksArray(), 8);
        filterService.printArray(filtered);

        System.out.println("\r\n------------Ordering a book 11/22/63---------");
        String titleOrder = "11/22/63";
        ordered=filterService.findBookTitle(titleOrder);
        Order order=new Order(ordered, user, LocalDate.now(), LocalDate.now().plusWeeks(3), false);
        orderService.orderSubmit(order);

        String addressBox="Khreshchatyk";
        box.setAddress(addressBox);
        boxService.addBox(box);
        boxService.setService(orderService);
        boxService.receive(box, b2);

        System.out.println("---Info about your order---");
        order.showInfoOrder();

        System.out.println("\r\n----INFO FOR MANAGER: Deliveries to be done----");
        deliveryService.setDataServer(orderService.getData());
        deliveryService.deliveryInfo();
        BoxService deliveryBox = deliveryService.boxService;
        deliveryBox.setService(orderService);
        deliveryService.deliverToBox(order, box);

        System.out.println("Your order is delivered to box");

        System.out.println("\r\nINFO FOR MANAGER: List of the books in the box at " + box.getAddress() + ":");
        filterService.printArray(box.getArrayBook());

        boxService.checkOut(box, order);
        System.out.println("You received your order! Enjoy!");

        System.out.println("\r\nUPD INFO FOR MANAGER: List of the books in the box");
        filterService.printArray(box.getArrayBook());

        System.out.println("\r\nINFO FOR MANAGER: Book - User");
        DataServer orderData = orderService.getData();
        orderData.infoBelongList();

        System.out.println("\r\n------INFO FOR MANAGER: OVERDUE ORDERS------");
        ArrayList<Order> overdueOrders;
        overdueOrders = communicationService.checkingDate(orderData.getOrdersArray());

        if(overdueOrders.size()==0)
            System.out.println("Overdue orders don't exist!");

        System.out.println("\r\n----WHOOPS The Three-Body Problem book needs repair----");
        b3.setRepair(true);
        System.out.println("----Calling Repairman " + repairman.getIdNumber() + "----");
        repairman.repair(b3);

        if (b3.getAvailability())
            System.out.println("book is repaired");

        System.out.println("\r\nUser Ox is trying to order same book 11/22/63");
        User secondUser = new User ("Ox");
        Order secondOrder = new Order(order.getBook(), secondUser, LocalDate.now(), LocalDate.now().plusWeeks(3), false);
        orderService.orderSubmit(secondOrder);

        if(orderService.getData().getWaitingList().get(b1).size()!=0)
            System.out.println("Ox is in Waiting List");
    }
}
