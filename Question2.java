import java.util.Arrays;

public class Question2 {

    public static int minMovesToEqualize(int[] dresses) {
        int n = dresses.length;
        int totalDresses = Arrays.stream(dresses).sum();

        // Check if total dresses can be evenly distributed
        if (totalDresses % n != 0) {
            return -1; // Not possible to equalize
        }

        int targetDresses = totalDresses / n;
        int moves = 0;

        for (int i = 0; i < n; i++) {
            int diff = dresses[i] - targetDresses;
            moves += Math.abs(diff); // Add the absolute difference
        }

        return moves / 2; // Since each move affects two sewing machines
    }

    public static void main(String[] args) {
        int[] dresses = {1, 0, 5}; // Example input
        int result = minMovesToEqualize(dresses);
        System.out.println("Minimum moves to equalize dresses: " + result);
    }
}
