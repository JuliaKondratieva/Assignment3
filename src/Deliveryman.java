public class Deliveryman {
    private int idNumber;

    public void deliveryToBox(Order order, Box box){
        String adress=box.getAdress();
    }

    public void deliveryToUser(Order order){
        String adress=order.getUser().getAdress();
    }
}
