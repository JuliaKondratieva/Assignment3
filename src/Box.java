import java.util.ArrayList;

public class Box {
    private ArrayList<Book> books;
    private String adress;

    Box()
    {
        books=null;
        adress="";
    }
    Box(ArrayList<Book> books, String adress){
        this();
        this.books=books;
        this.adress=adress;
    }

    public ArrayList<Book> extradition(Book pickedUp){
    books.remove(pickedUp);
    return books;
    }

    public ArrayList<Book> addingBook(Book left){
        books.add(left);
        return books;
    }
    public String getAdress() {
        return adress;
    }
}
