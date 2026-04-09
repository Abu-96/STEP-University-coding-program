import java.util.HashSet;
import java.util.Set;

public class UC3TrainApp {

    public static void main(String[] args){

        System.out.println("=======================================");
        System.out.println("UC3 - Track Unique Bogie IDs");
        System.out.println("=======================================");

        Set<String> bogies = new HashSet<>();
        
        //Add bogies IDs
        bogies.add("BG101");
        bogies.add("BG102");
        bogies.add("BG103");
        bogies.add("BG104");

        // Duplicate Entries will be ignored
        bogies.add("BG101");
        bogies.add("BG102");
        
        System.out.println("Bogie IDs After Insertion: ");
        System.out.println(bogies);


    }
}