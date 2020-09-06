package com.labs.a1;

public class DeliveryService {
    BoxService boxService;
    public DeliveryService(){
        boxService=new BoxService();
    }
    public void deliverToBox(Order order, Box box){
        boxService.receive(box, order.getBook());
    }

    public void deliverToUser(Order order, User user){
        order.setDelivered(true);
    }
}
