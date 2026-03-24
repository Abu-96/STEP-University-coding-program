/**
 * UseCase6RoomAllocationService
 * 
 * Demonstrates booking confirmation and safe room allocation.
 * Prevents double-booking using Set and maintains inventory consistency.
 * 
 * @author Abu
 * @version 6.1
 */

import java.util.*;

// ===== Reservation (Booking Request) =====
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// ===== Booking Queue (FIFO) =====
class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNextRequest() {
        return queue.poll(); // removes from queue (FIFO)
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// ===== Inventory Service =====
class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrement(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("\n---- Current Inventory ----");
        for (Map.Entry<String, Integer> e : inventory.entrySet()) {
            System.out.println(e.getKey() + " → " + e.getValue());
        }
    }
}

// ===== Booking Service (Core Logic) =====
class BookingService {

    private RoomInventory inventory;

    // Map: RoomType → Set of allocated room IDs
    private HashMap<String, Set<String>> allocatedRooms = new HashMap<>();

    // Global set to ensure uniqueness
    private Set<String> allAllocatedRoomIds = new HashSet<>();

    // Counter for ID generation
    private int idCounter = 1;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    // Process booking requests
    public void processBookings(BookingRequestQueue queue) {

        while (!queue.isEmpty()) {

            Reservation request = queue.getNextRequest();
            String roomType = request.getRoomType();

            System.out.println("\nProcessing request for " + request.getGuestName());

            int available = inventory.getAvailability(roomType);

            if (available > 0) {

                // Generate unique room ID
                String roomId = generateRoomId(roomType);

                // Store allocation
                allocatedRooms
                        .computeIfAbsent(roomType, k -> new HashSet<>())
                        .add(roomId);

                allAllocatedRoomIds.add(roomId);

                // Update inventory
                inventory.decrement(roomType);

                // Confirm booking
                System.out.println("Booking Confirmed!");
                System.out.println("Guest: " + request.getGuestName());
                System.out.println("Room Type: " + roomType);
                System.out.println("Assigned Room ID: " + roomId);

            } else {
                System.out.println("Booking Failed (No Availability) for " + request.getGuestName());
            }
        }
    }

    // Unique Room ID Generator
    private String generateRoomId(String roomType) {
        String prefix = roomType.substring(0, 2).toUpperCase();
        String roomId;

        do {
            roomId = prefix + idCounter++;
        } while (allAllocatedRoomIds.contains(roomId));

        return roomId;
    }
}

// ===== Main Class =====
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay!");
        System.out.println("Hotel Booking System v6.1\n");

        // Initialize components
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();
        BookingService bookingService = new BookingService(inventory);

        // Add booking requests
        queue.addRequest(new Reservation("Abu", "Single Room"));
        queue.addRequest(new Reservation("Rahul", "Single Room"));
        queue.addRequest(new Reservation("Sneha", "Single Room")); // should fail (only 2 available)
        queue.addRequest(new Reservation("John", "Suite Room"));

        // Process bookings (FIFO)
        bookingService.processBookings(queue);

        // Show final inventory
        inventory.displayInventory();
    }
}