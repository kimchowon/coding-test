package thisiscodingtest.dp;

import java.io.*;
import java.util.StringTokenizer;

public class PracticalProblem02 {

    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int[] array = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        dp[0] = array[0];
        dp[1] = array[1];

        for (int i=2; i < N; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+array[i]);
        }

        bw.write(dp[N-1] + "\n");

        br.close();
        bw.flush();
        bw.close();
    }
}
