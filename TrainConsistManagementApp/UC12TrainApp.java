import java.util.ArrayList;
import java.util.List;

// GoodsBogie class
class GoodsBogie {
    String type;   // e.g., Cylindrical, Open, Box
    String cargo;  // e.g., Petroleum, Coal, Grain

    GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return type + " -> " + cargo;
    }
}

public class UC12TrainApp {

    public static void main(String[] args) {

        // Welcome Message
        System.out.println("=== Train Consist Management App ===");

        // Create list of goods bogies
        List<GoodsBogie> bogies = new ArrayList<>();
        bogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        bogies.add(new GoodsBogie("Open", "Coal"));
        bogies.add(new GoodsBogie("Box", "Grain"));

        // Safety validation using stream
        boolean isSafe = bogies.stream()
                .allMatch(b ->
                        !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum")
                );

        // Display result
        System.out.println("Is Train Safety Compliant? " + (isSafe ? "YES" : "NO"));

    }
}