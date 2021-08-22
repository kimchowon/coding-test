package baekjoon.silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Test01 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(br.readLine());
            solution(num);
        }
    }

    public static void solution(int n) {
        int[] fib = new int[n + 1];

        if (n == 0) {
            System.out.println("1 0");
            return;
        }

        if (n==1) {
            System.out.println("0 1");
            return;
        }

        fib[0] = 0;
        fib[1] = 1;
        for (int i=2; i <=n; i++) {
            fib[i] = fib[i-1] + fib[i-2];
        }
        System.out.println(fib[n-1] + " " + fib[n]);
    }
}
