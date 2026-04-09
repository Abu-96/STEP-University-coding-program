import java.util.LinkedHashSet;

public class UC5TrainApp {

    public static void main(String[] args) {

        // Welcome Message
        System.out.println("=== Train Consist Management App ===");

        // Create LinkedHashSet for train formation
        LinkedHashSet<String> trainFormation = new LinkedHashSet<>();

        // Add bogies
        trainFormation.add("Engine");
        trainFormation.add("Sleeper");
        trainFormation.add("Cargo");
        trainFormation.add("Guard");

        // Attempt to add duplicate
        trainFormation.add("Sleeper"); // duplicate (ignored)

        // Display final formation
        System.out.println("Train Formation: " + trainFormation);
    }
}