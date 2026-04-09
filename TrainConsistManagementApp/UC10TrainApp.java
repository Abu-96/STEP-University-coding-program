import java.util.ArrayList;
import java.util.List;

// Bogie class (same as previous UCs)
class Bogie {
    String name;
    int capacity;

    Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
}

public class UC10TrainApp {

    public static void main(String[] args) {

        // Welcome Message
        System.out.println("=== Train Consist Management App ===");

        // Create list of bogies
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 54));
        bogies.add(new Bogie("First Class", 24));

        // Calculate total seating capacity using stream
        int totalSeats = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        // Display total capacity
        System.out.println("Total Seating Capacity: " + totalSeats);

    }
}