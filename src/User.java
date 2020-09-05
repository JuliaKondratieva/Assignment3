public class User {
    private String username;
    private int idNumber;
    private String adress;

    public User() {
        username = "";
        idNumber = 0;
        adress = "";
    }

    public User(String username, int idNumber, String adress) {
        this.username = username;
        this.idNumber = idNumber;
        this.adress=adress;
    }
    public String getAdress() {
        return adress;
    }

}
