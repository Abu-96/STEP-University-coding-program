/**
 * UseCase5BookingRequestQueue
 * 
 * Demonstrates booking request intake using a Queue (FIFO).
 * Requests are stored and processed in arrival order.
 * No inventory updates occur at this stage.
 * 
 * @author Abu
 * @version 5.1
 */

import java.util.*;

// ===== Reservation Class (Represents booking request) =====
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

    public void display() {
        System.out.println("Guest: " + guestName + " | Requested: " + roomType);
    }
}

// ===== Booking Queue (FIFO) =====
class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request added for " + reservation.getGuestName());
    }

    // View all requests (without removing)
    public void displayQueue() {
        System.out.println("\n---- Booking Request Queue ----\n");

        for (Reservation r : queue) {
            r.display();
        }
    }

    // Get next request (peek, no removal)
    public Reservation peekNext() {
        return queue.peek();
    }
}

// ===== Main Class =====
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay!");
        System.out.println("Hotel Booking System v5.1\n");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulate incoming booking requests
        bookingQueue.addRequest(new Reservation("Abu", "Single Room"));
        bookingQueue.addRequest(new Reservation("Rahul", "Double Room"));
        bookingQueue.addRequest(new Reservation("Sneha", "Suite Room"));

        // Display queue (FIFO order)
        bookingQueue.displayQueue();

        // Show next request to be processed
        System.out.println("\nNext request to process:");
        Reservation next = bookingQueue.peekNext();

        if (next != null) {
            next.display();
        }

        System.out.println("\nNote: No rooms allocated yet. Queue only stores requests.");
    }
}