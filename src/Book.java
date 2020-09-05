public class Book {
    private String title;
    private String author;
    private long year;
    private double rating;
    private Genre genre;
    private boolean available;
    private boolean needRepair;
    private boolean isDelivered;

    Book()
    {
        title="";
        author="";
        year=0;
        rating=0;
        genre=null;
        available=true;
        needRepair=false;
        isDelivered=false;
    }
    Book(String title, String author, long year, double rating, Genre genre, boolean available, boolean needRepair, boolean isDelivered){
        //this();
        this.title=title;
        this.author=author;
        this.year=year;
        this.rating=rating;
        this.genre=genre;
        this.available=available;
        this.needRepair=needRepair;
        this.isDelivered=isDelivered;
    }
    public String getTitle(){
        return title;
    }
    public boolean getDelivered() {
        return isDelivered;
    }
    public void setDelivered(boolean delivered) {
        this.isDelivered=delivered;
    }
    public Genre getGenre(){
        return genre;
    }
    public double getRating(){
        return rating;
    }
    public String getAuthor(){
        return author;
    }
    public long getYear(){
        return year;
    }
    public boolean getAvailability(){
        return available;
    }
    public boolean getRepair(){
        return needRepair;
    }
    public void setRepair(boolean need) {
        this.needRepair=need;
    }
    public void setAvailability(boolean availability) {
        this.available = availability;
    }
}
