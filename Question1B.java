public class Question1B {
    public static void main(String[] args) {
        int[] engines = {1, 2, 3};
        int k = 1;
        System.out.println(minimumTime(engines, k));
    }

    public static int minimumTime(int[] engines, int k) {
        int maxTime = 0;
        int engineers = 1;

        for (int i = engines.length - 1; i >= 0; i--) {
            int time = engines[i];
            while (engineers < time) {
                int splitTime = Math.min(time - engineers, k);
                engineers += splitTime;
                
                maxTime = Math.max(maxTime, time);
            }
            engineers--;
        }

        return maxTime + 1;
    }
}
