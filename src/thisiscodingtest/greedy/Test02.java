package thisiscodingtest.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Test02 {
    public static int[][] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        cards = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cards[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = solution();

        bw.write(max + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static int solution() {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < cards.length; i++) {
            int min = cards[i][0];
            for (int j = 0; j < cards[i].length; j++) {
                min = Math.min(min, cards[i][j]);
            }
            max = Math.max(max, min);
        }
        return max;
    }
}
