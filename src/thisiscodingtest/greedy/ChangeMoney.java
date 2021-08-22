package thisiscodingtest.greedy;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * 거스름돈 문제
 */
public class ChangeMoney {
    public static int answer = 0;
    public static void main(String[] args) throws IOException {
        int[] moneys = {10, 100, 50, 500};
        int N = 1260;

        greedy(moneys, N);
        System.out.println(answer);

    }

    public static void greedy(int[] moneys, int N) {
        Arrays.sort(moneys); // 화폐를 정렬(오름차순)
        for (int i=moneys.length-1; i >=0; i--) { // 큰수부터 꺼냄
            if (N == 0) {
                break;
            }

            int count = N / moneys[i];
            answer+=count;
            N = N % moneys[i];
        }
    }
}
