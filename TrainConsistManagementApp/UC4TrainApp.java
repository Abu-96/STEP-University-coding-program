import java.util.LinkedList;

public class UC4TrainApp {

    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println("UC4 - Maintain Ordered Bogie Consist");
        System.out.println("=======================================");

        LinkedList<String> trainConsist = new LinkedList<>();
        
        // Add bogies IDs
        trainConsist.add("Engine");
        trainConsist.add("Sleeper");
        trainConsist.add("AC");
        trainConsist.add("Cargo");
        trainConsist.add("Gaurd");

        System.out.println("\nInitial Train Consist:");
        System.out.println( trainConsist);


        trainConsist.add(2, "Pantry Car");
        
        System.out.println("\nAfter Inserting 'Pantry Car' at position 2:");
        System.out.println(trainConsist);

        trainConsist.removeFirst();
        trainConsist.removeLast();

        System.out.println("\nAfter Removing First and Last Bogie:");
        System.out.println(trainConsist);


    }
}