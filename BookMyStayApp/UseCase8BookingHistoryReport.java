/**
 * UseCase8BookingHistoryReport
 * 
 * Demonstrates storing confirmed bookings and generating reports
 * using List (ordered storage) and a separate reporting service.
 * 
 * @author Abu
 * @version 8.1
 */

import java.util.*;

// ===== Reservation (Confirmed Booking) =====
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private double price;

    public Reservation(String reservationId, String guestName, String roomType, double price) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void display() {
        System.out.println("ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room: " + roomType +
                " | Price: ₹" + price);
    }
}

// ===== Booking History (Storage) =====
class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    // Add confirmed reservation
    public void addReservation(Reservation r) {
        history.add(r);
    }

    // Retrieve all bookings
    public List<Reservation> getAllReservations() {
        return history;
    }
}

// ===== Reporting Service =====
class BookingReportService {

    private BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    // Display all bookings
    public void displayAllBookings() {
        System.out.println("\n---- Booking History ----\n");

        List<Reservation> list = history.getAllReservations();

        if (list.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Reservation r : list) {
            r.display();
        }
    }

    // Generate summary report
    public void generateSummary() {
        List<Reservation> list = history.getAllReservations();

        int totalBookings = list.size();
        double totalRevenue = 0;

        Map<String, Integer> roomCount = new HashMap<>();

        for (Reservation r : list) {
            totalRevenue += r.getPrice();

            roomCount.put(
                r.getRoomType(),
                roomCount.getOrDefault(r.getRoomType(), 0) + 1
            );
        }

        System.out.println("\n---- Booking Summary ----\n");
        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Total Revenue: ₹" + totalRevenue);

        System.out.println("\nBookings by Room Type:");
        for (Map.Entry<String, Integer> e : roomCount.entrySet()) {
            System.out.println(e.getKey() + " → " + e.getValue());
        }
    }
}

// ===== Main Class =====
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay!");
        System.out.println("Hotel Booking System v8.1\n");

        // Initialize history
        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings (from Use Case 6)
        history.addReservation(new Reservation("R101", "Abu", "Single Room", 1500));
        history.addReservation(new Reservation("R102", "Rahul", "Double Room", 2500));
        history.addReservation(new Reservation("R103", "Sneha", "Suite Room", 5000));
        history.addReservation(new Reservation("R104", "John", "Single Room", 1500));

        // Initialize reporting service
        BookingReportService reportService = new BookingReportService(history);

        // Display all bookings
        reportService.displayAllBookings();

        // Generate summary report
        reportService.generateSummary();

        System.out.println("\nNote: Reporting does not modify booking history.");
    }
}