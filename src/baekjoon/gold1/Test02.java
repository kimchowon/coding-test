package baekjoon.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test02 {
    public static int[] pages = new int[10]; // 0 ~ 9의 등장 횟수를 담는 배열
    public static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        solution(N);
    }

    public static void solution(long N) {
        long A = 1;
        long B = N;

        while (A <= B) {
            // 1의 자리
            int oneA = (int) (A % 10);
            int oneB = (int) (B % 10);

            if (oneB < 9) {
                for (int i = 1; i <= oneB + 1; i++) {
                    // B가 0보다 작거나 같다면 계산을 몀춘다.
                    if (B <= 0) {
                        break;
                    }
                    cal(B);
                    B--;
                }
            }

            if (B < A) {
                break;
            }

            if (oneA > 0) {
                for (int i = 1; i <= 10 - oneA; i++) {
                    cal(A);
                    A++;
                }
            }

            for (int i = 0; i < pages.length; i++) {
                pages[i] += (B / 10 - A / 10 + 1) * count;
            }

            count *= 10;
            A = A / 10;
            B = B / 10;
        }

        for (int i = 0; i < pages.length; i++) {
            System.out.print(pages[i] + " ");
        }
    }

    public static void cal(long N) {
        while (N > 0) {
            int num = (int) (N % 10);
            pages[num] += count;
            N = N / 10;
        }
    }
}

