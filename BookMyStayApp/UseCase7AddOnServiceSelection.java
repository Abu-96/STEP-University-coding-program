/**
 * UseCase7AddOnServiceSelection
 * 
 * Demonstrates attaching optional add-on services to reservations
 * without modifying booking or inventory logic.
 * 
 * @author Abu
 * @version 7.1
 */

import java.util.*;

// ===== Reservation (Minimal for mapping) =====
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// ===== Add-On Service =====
class AddOnService {
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

// ===== Add-On Service Manager =====
class AddOnServiceManager {

    // Map: ReservationID → List of Services
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {
        serviceMap
            .computeIfAbsent(reservationId, k -> new ArrayList<>())
            .add(service);

        System.out.println("Added service: " + service.getServiceName() +
                " to Reservation: " + reservationId);
    }

    // Display services for a reservation
    public void displayServices(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        System.out.println("\nServices for Reservation " + reservationId + ":");

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        for (AddOnService s : services) {
            System.out.println("- " + s.getServiceName() + " (₹" + s.getCost() + ")");
        }
    }

    // Calculate total add-on cost
    public double calculateTotalCost(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null) return 0;

        double total = 0;
        for (AddOnService s : services) {
            total += s.getCost();
        }
        return total;
    }
}

// ===== Main Class =====
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay!");
        System.out.println("Hotel Booking System v7.1\n");

        // Existing reservation (already booked in Use Case 6)
        Reservation r1 = new Reservation("R101", "Abu", "Single Room");

        // Initialize Add-On Manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Guest selects services
        manager.addService(r1.getReservationId(), new AddOnService("Breakfast", 300));
        manager.addService(r1.getReservationId(), new AddOnService("Airport Pickup", 800));
        manager.addService(r1.getReservationId(), new AddOnService("Extra Bed", 500));

        // Display selected services
        manager.displayServices(r1.getReservationId());

        // Calculate total add-on cost
        double total = manager.calculateTotalCost(r1.getReservationId());

        System.out.println("\nTotal Add-On Cost: ₹" + total);

        System.out.println("\nNote: Booking and inventory remain unchanged.");
    }
}