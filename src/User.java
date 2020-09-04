public class User {
    private String username;
    private int idNumber;
    private String adress;

    User() {
        username = "";
        idNumber = 0;
        adress="";
    }

    User(String username, int idNumber, String adress) {
        this();
        this.username = username;
        this.idNumber = idNumber;
        this.adress=adress;
    }
    public String getAdress() {
        return adress;
    }
    public void orderBook(Book book){

    }
    public void leaveBook(Book book){

    }
}
