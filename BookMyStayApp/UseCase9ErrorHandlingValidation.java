/**
 * UseCase9ErrorHandlingValidation
 * 
 * Demonstrates input validation, custom exceptions, and fail-fast design
 * to ensure system reliability and prevent invalid state changes.
 * 
 * @author Abu
 * @version 9.1
 */

import java.util.*;

// ===== Custom Exception =====
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

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

// ===== Inventory =====
class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0); // no availability
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, -1);
    }

    public void decrement(String roomType) throws InvalidBookingException {
        int current = getAvailability(roomType);

        if (current <= 0) {
            throw new InvalidBookingException("No availability for " + roomType);
        }

        inventory.put(roomType, current - 1);
    }
}

// ===== Validator =====
class BookingValidator {

    private RoomInventory inventory;

    public BookingValidator(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void validate(Reservation r) throws InvalidBookingException {

        // Validate guest name
        if (r.getGuestName() == null || r.getGuestName().trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        // Validate room type
        int availability = inventory.getAvailability(r.getRoomType());

        if (availability == -1) {
            throw new InvalidBookingException("Invalid room type: " + r.getRoomType());
        }

        if (availability <= 0) {
            throw new InvalidBookingException("Room not available: " + r.getRoomType());
        }
    }
}

// ===== Booking Service =====
class BookingService {

    private RoomInventory inventory;
    private BookingValidator validator;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        this.validator = new BookingValidator(inventory);
    }

    public void processBooking(Reservation r) {

        try {
            // Validate first (Fail-Fast)
            validator.validate(r);

            // If valid → proceed
            inventory.decrement(r.getRoomType());

            System.out.println("Booking Confirmed for " + r.getGuestName()
                    + " (" + r.getRoomType() + ")");

        } catch (InvalidBookingException e) {
            // Graceful error handling
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }
}

// ===== Main Class =====
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay!");
        System.out.println("Hotel Booking System v9.1\n");

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        // Test cases (valid + invalid)

        // Valid booking
        service.processBooking(new Reservation("Abu", "Single Room"));

        // Invalid room type
        service.processBooking(new Reservation("Rahul", "Deluxe Room"));

        // No availability
        service.processBooking(new Reservation("Sneha", "Suite Room"));

        // Empty name
        service.processBooking(new Reservation("", "Double Room"));

        // Another valid booking
        service.processBooking(new Reservation("John", "Double Room"));

        System.out.println("\nSystem continues running safely after errors.");
    }
}