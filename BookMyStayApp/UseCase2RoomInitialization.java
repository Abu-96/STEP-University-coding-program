/**
 * UseCase2RoomInitialization
 * 
 * Demonstrates room modeling with updated sizes.
 * 
 * @author Abu
 * @version 2.1
 */

abstract class Room {
    protected int beds;
    protected int size; // in square feet
    protected double price;

    public Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq.ft");
        System.out.println("Price: ₹" + price);
    }

    public abstract String getRoomType();
}

class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500);  // changed from 200 → 250
    }

    @Override
    public String getRoomType() {
        return "Single Room";
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500);  // changed from 350 → 400
    }

    @Override
    public String getRoomType() {
        return "Double Room";
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000);  // changed from 600 → 750
    }

    @Override
    public String getRoomType() {
        return "Suite Room";
    }
}

public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay!");
        System.out.println("Hotel Booking System v2.1\n");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("---- Room Details ----\n");

        System.out.println(single.getRoomType());
        single.displayDetails();
        System.out.println("Available: " + singleAvailable + "\n");

        System.out.println(doubleRoom.getRoomType());
        doubleRoom.displayDetails();
        System.out.println("Available: " + doubleAvailable + "\n");

        System.out.println(suite.getRoomType());
        suite.displayDetails();
        System.out.println("Available: " + suiteAvailable + "\n");

        System.out.println("Thank you for using Book My Stay!");
    }
}