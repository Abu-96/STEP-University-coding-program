import java.util.ArrayList;
import java.util.List;

public class UC1TrainApp {

    public static void main(String[] args) {

        // Welcome Message
        System.out.println("=== Train Consist Management App ===");

        // Initialize empty consist
        List<String> trainConsist = new ArrayList<>();

        // Display initial bogie count
        System.out.println("Initial bogie count: " + trainConsist.size());

        System.out.println("Current Train Consist : " + trainConsist);
    }
}