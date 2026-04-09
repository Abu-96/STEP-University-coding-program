import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UC11TrainApp {

    public static void main(String[] args) {

        // Welcome Message
        System.out.println("=== Train Consist Management App ===");

        // Sample Inputs (can be replaced with user input)
        String trainId = "TRN-1234";
        String cargoCode = "PET-AB";

        // Define regex patterns
        String trainIdRegex = "TRN-\\d{4}";
        String cargoCodeRegex = "PET-[A-Z]{2}";

        // Compile patterns
        Pattern trainPattern = Pattern.compile(trainIdRegex);
        Pattern cargoPattern = Pattern.compile(cargoCodeRegex);

        // Create matchers
        Matcher trainMatcher = trainPattern.matcher(trainId);
        Matcher cargoMatcher = cargoPattern.matcher(cargoCode);

        // Validate inputs
        boolean isTrainValid = trainMatcher.matches();
        boolean isCargoValid = cargoMatcher.matches();

        // Display results
        System.out.println("Train ID: " + trainId + " -> " +
                (isTrainValid ? "Valid" : "Invalid"));

        System.out.println("Cargo Code: " + cargoCode + " -> " +
                (isCargoValid ? "Valid" : "Invalid"));

    }
}