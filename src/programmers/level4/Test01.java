package programmers.level4;

import java.util.Arrays;
import java.util.Stack;

public class Test01 {

    public static void main(String[] args) {

  /*      int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;*/

        int distance = 16;
        int[] rocks = {4, 8, 2};
        int n = 2;

        System.out.println(solution(distance, rocks, n));
    }

    public static int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks);

        int[] intervals = new int[rocks.length + 1];
        intervals[0] = rocks[0];
        intervals[intervals.length - 1] = distance - rocks[rocks.length - 1];
        for (int i = 1; i < intervals.length - 1; i++) {
            intervals[i] = Math.abs(rocks[i] - rocks[i - 1]);
        }

        int max = distance;
        int min = distance;

        while (min <= max) {
            int cnt = 0;
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < intervals.length; i++) {
                if (stack.isEmpty()) {
                    stack.push(intervals[i]);
                    continue;
                }

                int peek = stack.peek();
                if (peek < min) {
                    int num = intervals[i] + stack.pop();
                    stack.push(num);
                    cnt++;
                    continue;
                } else {
                    stack.push(intervals[i]);
                }
            }

            if (cnt <= n) {
                answer = min;
            }

            if (min + 1 == max) {
                break;
            }

            if (cnt > n) {
                max = min;
                min = min / 2;
            } else if (cnt <= n) {
                min = (max + min) / 2;
            }
        }
        return answer;
    }
}
