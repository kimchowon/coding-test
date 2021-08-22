package thisiscodingtest.dp;

import java.io.*;

public class StudyTest01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    /*    7
        3
        7
        5
        2
        6
        1
        4*/

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N];
        for(int i=0; i < N; i++) {
            dp[i]  = Integer.parseInt(br.readLine());
        }



        br.close();
        bw.flush();
        bw.close();
    }
}
