package com.labs.a1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Queue;

public class CommunicationService {

    public ArrayList<Order> checkingDate(Queue<Order> orders) {
        ArrayList<Order> overdueOrders=new ArrayList<>();
        LocalDate date = LocalDate.now();

        for(Order order:orders) {
            if (order.getDueDate().compareTo(date) <= 0) {
                overdueOrders.add(order);
                order.showInfoOrder();
            }
        }

        return overdueOrders;
    }
}
