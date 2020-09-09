package com.labs.a1;

public class DeliveryService {
    Deliveryman deliveryman;
    BoxService boxService;
    DataServer dataServer;

    public DeliveryService(){
        boxService=new BoxService();
        deliveryman=new Deliveryman();
        dataServer=new DataServer();
    }

    public void deliverToBox(Order order, Box box){
        boxService.receive(box, order.getBook());
    }

    public void deliveryInfo() {
        deliveryman.showInfo();
        dataServer.ordersOutput();
    }

    public void setDataServer(DataServer dataServer) {
        this.dataServer = dataServer;
    }
}
