import java.util.ArrayList;
import java.util.Collections;

public class Question1B {

    public static int minTimeToBuildEngines(int[] engines, int splitCost) {
        int engineers = 1;  // Initially, there is only one engineer
        int totalTime = 0;
        ArrayList<Integer> enginesList = new ArrayList<>();
        for (int engine : engines) {
            enginesList.add(engine);
        }
        while (!enginesList.isEmpty()) {
            if (engineers == 1) {
                // If there's only one engineer, split into two engineers
                totalTime += splitCost;
                engineers *= 2;
            } else {
                // Find the fastest engine to build
                int minTimeEngine = Collections.min(enginesList);
                totalTime += minTimeEngine;
                enginesList.remove(Integer.valueOf(minTimeEngine));
                // Check if splitting engineers would be beneficial
                if (engineers % 2 == 0) {
                    // If there are even number of engineers, split one of them
                    totalTime += splitCost;
                    engineers += 1;
                } else {
                    // If there are odd number of engineers, split the engineer with the longest remaining task
                    int maxTimeEngine = Collections.max(enginesList);
                    totalTime += splitCost;
                    enginesList.remove(Integer.valueOf(maxTimeEngine));
                    engineers += 1;
                }
            }
        }
        return totalTime;
    }

    public static void main(String[] args) {
        int[] engines = {3, 4, 5, 2};
        int splitCost = 2;
        System.out.println(minTimeToBuildEngines(engines, splitCost)); 
    }
}

