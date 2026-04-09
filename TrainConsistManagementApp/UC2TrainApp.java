import java.util.ArrayList;
import java.util.List;

public class UC2TrainApp {

    public static void main(String[] args) {

        // Welcome Message
        System.out.println("=======================================");
        System.out.println("UC2 - Add passenger Bogies to Train ");
        System.out.println("=======================================");
        // Create ArrayList for passenger bogies
        List<String> passengerBogies = new ArrayList<>();

        // Add bogies
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        // Display bogies after insertion
        System.out.println("Passenger Bogies: " + passengerBogies);

        // Remove a bogie
        passengerBogies.remove("AC Chair");
        System.out.println("After removing AC Chair: " + passengerBogies);

        // Check existence
        if (passengerBogies.contains("Sleeper")) {
            System.out.println("Sleeper bogie exists in the train.");
        } else {
            System.out.println("Sleeper bogie not found.");
        }

        // Final state
        System.out.println("Final Passenger Bogies: " + passengerBogies);
    }
}