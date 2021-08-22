package thisiscodingtest.dp;

import java.io.*;

/**
 * 실전문제01. 1로 만들기
 * 풀이시간: 20분 / 소요시간: no..
 */
public class PracticalProblem01 {
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        dp[0] = dp[1] = 0;

        for (int i = 2; i <= N; i++) {
            int min = Integer.MAX_VALUE;
            if (i % 2 == 0) {
                min = Math.min(min, dp[i / 2] + 1);
            }

            if (i % 3 == 0) {
                min = Math.min(min, dp[i / 3] + 1);
            }

            if (i % 5 == 0) {
                min = Math.min(min, dp[i / 5] + 1);
            }

            min = Math.min(min, dp[i - 1] + 1);
            dp[i] = min;
        }

        bw.write(dp[N] + "\n");

        br.close();
        bw.flush();
        bw.close();
    }
}
