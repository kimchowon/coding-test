package programmers.level3;

import java.util.Arrays;

public class Test13 {
    public static void main(String[] args) {
        int n = 4;
        int s = 10;
        System.out.println(Arrays.toString(solution(n, s)));
    }

    public static int[] solution(int n, int s) {
        int[] answer = new int[n];

        if (n > s) {
            return new int[]{-1};
        }

        int quo = s / n;
        int reminder = s % n;

        for (int i = 1; i <= reminder; i++) {
            answer[answer.length - i] = quo + 1;
        }

        for (int i = 0; i < n - reminder; i++) {
            answer[i] = quo;
        }
        return answer;
    }
}
