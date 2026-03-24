/**
 * UseCase11ConcurrentBookingSimulation
 * 
 * Demonstrates concurrent booking handling using threads and synchronization
 * to prevent race conditions and ensure thread safety.
 * 
 * @author Abu
 * @version 11.1
 */

import java.util.*;

// ===== Reservation =====
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

// ===== Thread-Safe Booking Queue =====
class BookingQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    // synchronized add
    public synchronized void add(Reservation r) {
        queue.offer(r);
    }

    // synchronized remove
    public synchronized Reservation getNext() {
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}

// ===== Thread-Safe Inventory =====
class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    // Critical Section (synchronized)
    public synchronized boolean allocate(String roomType) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        }

        return false;
    }

    public void display() {
        System.out.println("\nFinal Inventory:");
        for (Map.Entry<String, Integer> e : inventory.entrySet()) {
            System.out.println(e.getKey() + " → " + e.getValue());
        }
    }
}

// ===== Booking Processor (Thread) =====
class BookingProcessor extends Thread {

    private BookingQueue queue;
    private RoomInventory inventory;

    public BookingProcessor(BookingQueue queue, RoomInventory inventory, String name) {
        super(name);
        this.queue = queue;
        this.inventory = inventory;
    }

    @Override
    public void run() {

        while (true) {

            Reservation r;

            // synchronized retrieval
            synchronized (queue) {
                if (queue.isEmpty()) break;
                r = queue.getNext();
            }

            if (r == null) break;

            // Critical section for allocation
            boolean success = inventory.allocate(r.getRoomType());

            if (success) {
                System.out.println(getName() + " → Booking Confirmed for "
                        + r.getGuestName() + " (" + r.getRoomType() + ")");
            } else {
                System.out.println(getName() + " → Booking Failed for "
                        + r.getGuestName() + " (No Availability)");
            }
        }
    }
}

// ===== Main Class =====
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay!");
        System.out.println("Hotel Booking System v11.1\n");

        // Shared resources
        BookingQueue queue = new BookingQueue();
        RoomInventory inventory = new RoomInventory();

        // Simulate multiple booking requests
        queue.add(new Reservation("Abu", "Single Room"));
        queue.add(new Reservation("Rahul", "Single Room"));
        queue.add(new Reservation("Sneha", "Single Room"));
        queue.add(new Reservation("John", "Double Room"));
        queue.add(new Reservation("Amit", "Double Room"));

        // Create multiple threads (simulating users)
        Thread t1 = new BookingProcessor(queue, inventory, "Thread-1");
        Thread t2 = new BookingProcessor(queue, inventory, "Thread-2");
        Thread t3 = new BookingProcessor(queue, inventory, "Thread-3");

        // Start threads
        t1.start();
        t2.start();
        t3.start();

        // Wait for completion
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Final inventory state
        inventory.display();

        System.out.println("\nAll bookings processed safely (no race conditions).");
    }
}