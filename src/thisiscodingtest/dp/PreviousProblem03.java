package thisiscodingtest.dp;

import java.io.*;
import java.util.StringTokenizer;

public class PreviousProblem03 {
    public static int N;
    public static int[] days;
    public static int[] pays;
    public static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        days = new int[N+1];
        pays = new int[N+1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            days[i] = Integer.parseInt(st.nextToken());
            pays[i] = Integer.parseInt(st.nextToken());
        }

       /* 7
        3 10
        5 20
        1 10
        1 20
        2 15
        4 40
        2 200*/

        solution();

        br.close();
        bw.flush();
        bw.close();
    }

    public static void solution() {
        for (int i=N; i >=1; i--) {
            if (i + days[i] > N+1) {
                pays[i] = 0;
            }else {
                // 일안하는 경우
                int num1 = pays[i+1];

                // 일하는 경우
                
            }
        }
    }
}
