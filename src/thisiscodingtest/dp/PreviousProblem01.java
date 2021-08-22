package thisiscodingtest.dp;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 기출문제01. 금광
 * 제한시간: 30분 / 소요시간: 25분 42초
 */
public class PreviousProblem01 {
    public static int[][] place;
    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            place = new int[N][M];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    place[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            solution();
            bw.write(answer + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void solution() {
        int max = Integer.MIN_VALUE;

        for (int i = 1; i < place[0].length; i++) {
            for (int j = 0; j < place.length; j++) {
                int num1 = checkValidation(j - 1, i - 1) ? place[j - 1][i - 1] : Integer.MIN_VALUE;
                int num2 = checkValidation(j, i - 1) ? place[j][i - 1] : Integer.MIN_VALUE;
                int num3 = checkValidation(j + 1, i - 1) ? place[j + 1][i - 1] : Integer.MIN_VALUE;

                place[j][i] = place[j][i] + Math.max(Math.max(num1, num2), num3);
                max = Math.max(max, place[j][i]);
            }
        }
        answer = max;
    }

    public static boolean checkValidation(int x, int y) {
        if (x < 0 || x >= place.length || y < 0 || y >= place[0].length) {
            return false;
        }
        return true;
    }
}
