/**
 * UseCase10BookingCancellation
 * 
 * Demonstrates safe cancellation of bookings with rollback using Stack (LIFO),
 * inventory restoration, and validation of cancellation requests.
 * 
 * @author Abu
 * @version 10.1
 */

import java.util.*;

// ===== Reservation =====
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private String roomId;

    public Reservation(String reservationId, String guestName, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public void display() {
        System.out.println("ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room: " + roomType +
                " | RoomID: " + roomId);
    }
}

// ===== Inventory =====
class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 0);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public void increment(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    public void display() {
        System.out.println("\n---- Inventory ----");
        for (Map.Entry<String, Integer> e : inventory.entrySet()) {
            System.out.println(e.getKey() + " → " + e.getValue());
        }
    }
}

// ===== Booking History =====
class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    public void add(Reservation r) {
        history.add(r);
    }

    public Reservation findById(String id) {
        for (Reservation r : history) {
            if (r.getReservationId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    public void remove(Reservation r) {
        history.remove(r);
    }

    public void display() {
        System.out.println("\n---- Booking History ----");
        if (history.isEmpty()) {
            System.out.println("No active bookings.");
            return;
        }
        for (Reservation r : history) {
            r.display();
        }
    }
}

// ===== Cancellation Service =====
class CancellationService {

    private RoomInventory inventory;
    private BookingHistory history;

    // Stack to track released room IDs (LIFO rollback)
    private Stack<String> rollbackStack = new Stack<>();

    // Track cancelled reservations
    private Set<String> cancelledReservations = new HashSet<>();

    public CancellationService(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
    }

    public void cancel(String reservationId) {

        System.out.println("\nProcessing cancellation for ID: " + reservationId);

        // Validate existence
        Reservation r = history.findById(reservationId);

        if (r == null) {
            System.out.println("Cancellation Failed: Reservation not found.");
            return;
        }

        // Prevent duplicate cancellation
        if (cancelledReservations.contains(reservationId)) {
            System.out.println("Cancellation Failed: Already cancelled.");
            return;
        }

        // Step 1: Record room ID (rollback)
        rollbackStack.push(r.getRoomId());

        // Step 2: Restore inventory
        inventory.increment(r.getRoomType());

        // Step 3: Update booking history
        history.remove(r);

        // Step 4: Mark as cancelled
        cancelledReservations.add(reservationId);

        System.out.println("Cancellation Successful!");
        System.out.println("Released Room ID: " + r.getRoomId());
    }

    public void displayRollbackStack() {
        System.out.println("\nRollback Stack (Recent Releases): " + rollbackStack);
    }
}

// ===== Main Class =====
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay!");
        System.out.println("Hotel Booking System v10.1\n");

        // Setup existing confirmed bookings
        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();

        history.add(new Reservation("R101", "Abu", "Single Room", "SI1"));
        history.add(new Reservation("R102", "Rahul", "Double Room", "DO1"));

        // Display initial state
        history.display();
        inventory.display();

        // Initialize cancellation service
        CancellationService service = new CancellationService(inventory, history);

        // Perform cancellations
        service.cancel("R101");  // valid
        service.cancel("R999");  // invalid
        service.cancel("R101");  // duplicate

        // Display final state
        history.display();
        inventory.display();
        service.displayRollbackStack();
    }
}