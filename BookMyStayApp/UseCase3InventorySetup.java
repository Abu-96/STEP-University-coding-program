/**
 * UseCase3InventorySetup
 * 
 * Demonstrates centralized room inventory management using HashMap.
 * Replaces scattered variables with a single source of truth.
 * 
 * @author Abu
 * @version 3.1
 */

import java.util.HashMap;
import java.util.Map;

// Inventory Class (Centralized Management)
class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor initializes inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Register room types with availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability of a specific room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability (controlled modification)
    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        } else {
            System.out.println("Room type not found: " + roomType);
        }
    }

    // Display full inventory
    public void displayInventory() {
        System.out.println("---- Current Room Inventory ----\n");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " → Available: " + entry.getValue());
        }
    }
}

// Main Class
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay!");
        System.out.println("Hotel Booking System v3.1\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Example: Update availability
        System.out.println("\nUpdating Single Room availability to 4...\n");
        inventory.updateAvailability("Single Room", 4);

        // Display updated inventory
        inventory.displayInventory();

        // Example: Fetch availability
        System.out.println("\nChecking availability for Suite Room:");
        int suiteCount = inventory.getAvailability("Suite Room");
        System.out.println("Suite Room Available: " + suiteCount);

        System.out.println("\nThank you for using Book My Stay!");
    }
}