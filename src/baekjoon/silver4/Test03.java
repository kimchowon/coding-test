package baekjoon.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test03 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] memo = new int[m + 1][n + 1];

            int answer = solution(m, n, memo);
            System.out.println(answer);
        }
    }

    public static int solution(int m, int n, int[][] memo) {
        if (m == n || n == 0) {
            return 1;
        }

        if (memo[m][n] > 0) {
            return memo[m][n];
        }

        memo[m][n] = solution(m - 1, n - 1, memo) + solution(m - 1, n, memo);
        return memo[m][n];
    }
}
