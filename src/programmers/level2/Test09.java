package programmers.level2;

import java.util.Arrays;

public class Test09 {
    public static void main(String[] args) {
        int brown = 24;
        int yellow = 24;

        System.out.println("answer : " + Arrays.toString(solution(brown, yellow)));
    }

    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int result1 = (brown + 4) / 2; // x + y
        int result2 = brown + yellow; // x * y

        int i = 1;
        while (answer[0] == 0 || answer[1] == 0) {
            int j = result1 - i;
            if (i * j == result2 && i >= j) {
                answer[0] = i;
                answer[1] = j;
            } else {
                i++;
            }
        }

        return answer;
    }
}
