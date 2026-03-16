public class PalindromeApp {

    public static void main(String[] args) {

        String input = "Level";

        System.out.println("Input : " + input);

        // Stack Strategy
        PalindromeStrategy stackStrategy = new StackStrategy();
        measurePerformance("Stack Strategy", stackStrategy, input);

        // Two Pointer Strategy
        PalindromeStrategy twoPointerStrategy = new TwoPointerStrategy();
        measurePerformance("Two Pointer Strategy", twoPointerStrategy, input);
    }

    /**
     * Measures execution time of a strategy.
     */
    private static void measurePerformance(String name,
                                           PalindromeStrategy strategy,
                                           String input) {

        long startTime = System.nanoTime();

        boolean result = strategy.check(input);

        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;

        System.out.println("\nAlgorithm : " + name);
        System.out.println("Is Palindrome? : " + result);
        System.out.println("Execution Time : " + executionTime + " ns");
    }
}

/**
 * Strategy Interface
 */
interface PalindromeStrategy {
    boolean check(String input);
}

/**
 * Stack Based Strategy
 */
class StackStrategy implements PalindromeStrategy {

    public boolean check(String input) {

        input = input.toLowerCase();

        java.util.Stack<Character> stack = new java.util.Stack<>();

        for (char c : input.toCharArray()) {
            stack.push(c);
        }

        for (char c : input.toCharArray()) {
            if (c != stack.pop()) {
                return false;
            }
        }

        return true;
    }
}

/**
 * Two Pointer Strategy (Optimized)
 */
class TwoPointerStrategy implements PalindromeStrategy {

    public boolean check(String input) {

        input = input.toLowerCase();

        int start = 0;
        int end = input.length() - 1;

        while (start < end) {
            if (input.charAt(start) != input.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }
}