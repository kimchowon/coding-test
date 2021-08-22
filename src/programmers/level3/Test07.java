package programmers.level3;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Test07 {
    public static void main(String[] args) {
        String[] operations =
                //{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
                //{"I 7", "I 5", "I -5", "D -1"};
                //{"I 16","D 1"};
                {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        System.out.println(Arrays.toString(solution(operations)));
    }

    public static int[] solution(String[] operations) {
        int[] answer = new int[2];
        String[] commands = {"D 1", "D -1"};
        PriorityQueue<Integer> pq_asc = new PriorityQueue<>(); // min heap
        PriorityQueue<Integer> pq_desc = new PriorityQueue<>(Collections.reverseOrder()); // max heap

        for (int i = 0; i < operations.length; i++) {
            String op = operations[i];

            if (op.startsWith("I")) {
                int num = Integer.parseInt(op.split(" ")[1]);
                pq_asc.add(num);
                pq_desc.add(num);

            } else if (op.equals(commands[0])) {
                if (!pq_desc.isEmpty()) {
                    int max = pq_desc.poll();
                    pq_asc.remove(max);
                }
            } else if (op.equals(commands[1])) {
                if (!pq_asc.isEmpty()) {
                    int min = pq_asc.poll();
                    pq_desc.remove(min);
                }
            }
        }

        int max = pq_desc.isEmpty() ? 0 : pq_desc.poll();
        int min = pq_asc.isEmpty() ? 0 : pq_asc.poll();

        answer[0] = max;
        answer[1] = min;
        return answer;
    }
}
