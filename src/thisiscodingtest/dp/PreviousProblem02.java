package thisiscodingtest.dp;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 기출문제02. 정수 삼각형
 * 제한시간: 30분 / 소요시간: ??? (흑흑..)
 */
public class PreviousProblem02 {
    public static int N;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = 0;
            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                dp[i][index] = num;
                index++;
            }
        }

        solution();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp[N - 1].length; i++) {
            max = Math.max(max, dp[N - 1][i]);
        }

        bw.write(max + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void solution() {
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] += dp[i - 1][j];
                } else if (j == i) {
                    dp[i][j] += dp[i - 1][j - 1];
                } else {
                    dp[i][j] += Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                }
            }
        }
    }
}
