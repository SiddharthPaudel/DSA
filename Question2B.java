import java.util.ArrayList;
import java.util.List;

public class Question2B{

    public static List<Integer> findIndividuals(int n, int[][] intervals, int firstPerson) {
        boolean[] knowsSecret = new boolean[n];
        knowsSecret[firstPerson] = true; // First person initially knows the secret
        
        // Iterate through intervals
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            // Share the secret with individuals in the interval
            for (int i = start; i <= end; i++) {
                knowsSecret[i] = true;
            }
        }
        
        // Store the individuals who know the secret
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (knowsSecret[i]) {
                result.add(i);
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] intervals = {{0, 2}, {1, 3}, {2, 4}};
        int firstPerson = 0;
        
        List<Integer> individuals = findIndividuals(n, intervals, firstPerson);
        System.out.println(individuals); // Output: [0, 1, 2, 3, 4]
    }
}
