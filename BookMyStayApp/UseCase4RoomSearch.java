/**
 * UseCase4RoomSearch
 * 
 * Demonstrates read-only room search using centralized inventory.
 * Only available rooms are displayed without modifying system state.
 * 
 * @author Abu
 * @version 4.1
 */

import java.util.*;

// ===== Abstract Room Class =====
abstract class Room {
    protected int beds;
    protected int size;
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

// ===== Concrete Room Classes =====
class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500);
    }

    public String getRoomType() {
        return "Single Room";
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500);
    }

    public String getRoomType() {
        return "Double Room";
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000);
    }

    public String getRoomType() {
        return "Suite Room";
    }
}

// ===== Inventory (Read Source Only) =====
class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0); // Example: unavailable
    }

    // Read-only access
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public Set<String> getAllRoomTypes() {
        return inventory.keySet();
    }
}

// ===== Search Service =====
class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms(List<Room> rooms) {

        System.out.println("---- Available Rooms ----\n");

        for (Room room : rooms) {
            int available = inventory.getAvailability(room.getRoomType());

            // Defensive check: only show available rooms
            if (available > 0) {
                System.out.println(room.getRoomType());
                room.displayDetails();
                System.out.println("Available: " + available + "\n");
            }
        }
    }
}

// ===== Main Class =====
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay!");
        System.out.println("Hotel Booking System v4.1\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize room objects
        List<Room> rooms = new ArrayList<>();
        rooms.add(new SingleRoom());
        rooms.add(new DoubleRoom());
        rooms.add(new SuiteRoom());

        // Initialize search service
        RoomSearchService searchService = new RoomSearchService(inventory);

        // Perform search (read-only)
        searchService.searchAvailableRooms(rooms);

        System.out.println("Search completed. No changes made to inventory.");
    }
}