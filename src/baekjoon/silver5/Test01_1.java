package baekjoon.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test01_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] chess = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                if (split[j].equals("W")) {
                    chess[i][j] = true;
                } else {
                    chess[i][j] = false;
                }
            }
        }
        solution(chess);
    }

    public static void solution(boolean[][] chess) {
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[i].length; j++) {
                if (i+7 < chess.length && j+7 < chess[0].length) {
                    boolean check = chess[i+7][j+7];
                    int count = 0;

                    for (int k = i; k <= i+7; k++) {
                        for (int l = j; l <= j+7; l++) {
                            if (chess[k][l] != check) {
                                count++;
                            }
                            check = !check;
                        }
                        check = !check;
                    }
                    answer = Math.min(answer, Math.min(count, 64 - count));
                }
            }
        }
        System.out.println(answer);
    }
}
