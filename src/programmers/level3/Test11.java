package programmers.level3;

import java.util.Collections;
import java.util.PriorityQueue;

public class Test11 {
    public static void main(String[] args) {

        int n = 3;
        int[] works =
                //{7, 5, 4, 3, 3};
                // {2, 1, 2};
                {1, 1};

        System.out.println("answer=" + solution(n, works));
    }

    public static long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // max heap

        // PriorityQueue에 배열을 모두 담아 자동 정렬
        for (int i = 0; i < works.length; i++) {
            pq.add(works[i]);
        }

        for (int i = 1; i <= n; i++) {
            int max = pq.poll();
            pq.add(max - 1);
        }

        while (!pq.isEmpty()) {
            int num = pq.poll();
            if (num > 0) {
                answer += Math.pow(num, 2);
            }
        }

        return answer;
    }
}
