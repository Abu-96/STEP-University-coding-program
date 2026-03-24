/**
 * UseCase12DataPersistenceRecovery
 * 
 * Demonstrates persistence using serialization and recovery using deserialization.
 * Ensures booking history and inventory survive application restarts.
 * 
 * @author Abu
 * @version 12.1
 */

import java.io.*;
import java.util.*;

// ===== Reservation (Serializable) =====
class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room: " + roomType);
    }
}

// ===== System State (Serializable) =====
class SystemState implements Serializable {
    private static final long serialVersionUID = 1L;

    Map<String, Integer> inventory;
    List<Reservation> bookings;

    public SystemState(Map<String, Integer> inventory, List<Reservation> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

// ===== Persistence Service =====
class PersistenceService {

    private static final String FILE_NAME = "hotel_state.ser";

    // Save state to file
    public void save(SystemState state) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(state);
            System.out.println("\nState saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    // Load state from file
    public SystemState load() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            SystemState state = (SystemState) ois.readObject();
            System.out.println("State loaded successfully.\n");
            return state;

        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.\n");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading state. Starting with safe defaults.\n");
        }

        // Return safe default state
        return new SystemState(new HashMap<>(), new ArrayList<>());
    }
}

// ===== Main Class =====
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay!");
        System.out.println("Hotel Booking System v12.1\n");

        PersistenceService service = new PersistenceService();

        // ===== Step 1: Load previous state =====
        SystemState state = service.load();

        Map<String, Integer> inventory = state.inventory;
        List<Reservation> bookings = state.bookings;

        // Initialize if empty (first run)
        if (inventory.isEmpty()) {
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 1);
        }

        // ===== Step 2: Display recovered state =====
        System.out.println("---- Recovered Inventory ----");
        for (Map.Entry<String, Integer> e : inventory.entrySet()) {
            System.out.println(e.getKey() + " → " + e.getValue());
        }

        System.out.println("\n---- Recovered Bookings ----");
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (Reservation r : bookings) {
                r.display();
            }
        }

        // ===== Step 3: Simulate new booking =====
        System.out.println("\nAdding new booking...");
        Reservation newBooking = new Reservation("R" + (bookings.size() + 101),
                "Guest" + (bookings.size() + 1), "Single Room");

        bookings.add(newBooking);

        // Update inventory safely
        inventory.put("Single Room", inventory.get("Single Room") - 1);

        // ===== Step 4: Save updated state =====
        service.save(new SystemState(inventory, bookings));

        System.out.println("\nSystem ready for next restart (state persisted).");
    }
}