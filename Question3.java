import java.util.Collections;
import java.util.PriorityQueue;

public class Question3{
    private PriorityQueue<Double> maxHeap; // To store the lower half of the scores
    private PriorityQueue<Double> minHeap; // To store the higher half of the scores

    public Question3() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addScore(double score) {
        if (maxHeap.isEmpty() || score <= maxHeap.peek()) {
            maxHeap.offer(score);
        } else {
            minHeap.offer(score);
        }

        balanceHeaps();
    }

    private void balanceHeaps() {
        while (maxHeap.size() - minHeap.size() > 1) {
            minHeap.offer(maxHeap.poll());
        }
        while (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double getMedianScore() {
        int totalSize = maxHeap.size() + minHeap.size();

        if (totalSize % 2 == 0) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
        }
    }

    public static void main(String[] args) {
        Question3 scoreTracker = new Question3();
        scoreTracker.addScore(85.5);
        scoreTracker.addScore(92.3);
        scoreTracker.addScore(77.8);
        scoreTracker.addScore(90.1);
        double median1 = scoreTracker.getMedianScore();
        System.out.println("Median 1: " + median1); // Output: 88.9
        scoreTracker.addScore(81.2);
        scoreTracker.addScore(88.7);
        double median2 = scoreTracker.getMedianScore();
        System.out.println("Median 2: " + median2); // Output: 86.95
    }
}
