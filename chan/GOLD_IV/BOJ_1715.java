package chan.GOLD_IV;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1715 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int [] cards = new int[n];
        for (int i = 0; i < n; i++) {
            cards[i] = scanner.nextInt();
        }
        int result = minComparisons(cards);
        System.out.println(result);

    }
    public static int minComparisons(int[] cards) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int card : cards) {
            priorityQueue.add(card);
        }
        int result = 0;

        while (priorityQueue.size() > 1) {
            int first = priorityQueue.poll();
            int second = priorityQueue.poll();
            int sum = first + second;
            result += sum;
            priorityQueue.add(sum);
        }
        return result;

    }
}
