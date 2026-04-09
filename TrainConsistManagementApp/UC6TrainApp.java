import java.util.HashMap;
import java.util.Map;

public class UC6TrainApp {

    public static void main(String[] args) {

        // Welcome Message
        System.out.println("=== Train Consist Management App ===");

        // Create HashMap for bogie-capacity mapping
        HashMap<String, Integer> bogieCapacity = new HashMap<>();

        // Add bogie capacities
        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 54);
        bogieCapacity.put("First Class", 24);

        // Display bogie capacities
        System.out.println("Bogie Capacity Details:");

        for (Map.Entry<String, Integer> entry : bogieCapacity.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

    }
}