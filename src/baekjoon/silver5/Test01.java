package baekjoon.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[][] chess = new String[N][M];
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                chess[i][j] = split[j];
            }
        }
        solution(chess);
    }

    public static String[][] startWithW = createChess("W");
    public static String[][] startWithB = createChess("B");

    public static void solution(String[][] chess) {
        int minValue = Integer.MAX_VALUE;
        for (int i = chess.length - 1; i >= 0; i--) {
            for (int j = chess[i].length - 1; j >= 0; j--) {

                int a = i - 7;
                int b = j - 7;

                if (a < 0 || b < 0) {
                    break;
                } else {
                    int countW = getCountChangeChessData(a, b, chess, "W");
                    int countB = getCountChangeChessData(a, b, chess, "B");
                    minValue = Math.min(minValue, Math.min(countW, countB));
                }
            }
        }
        System.out.println(minValue);
    }

    public static int getCountChangeChessData(int a, int b, String[][] chess, String chessData) {
        int count = 0;

        String[][] defaultChess;
        if (chessData.equals("W")) {
            defaultChess = startWithW;
        } else {
            defaultChess = startWithB;
        }

        int k = 0;
        for (int i = a; i <= a + 7; i++) {
            int l = 0;
            for (int j = b; j <= b + 7; j++) {
                if (!defaultChess[k][l].equals(chess[i][j])) {
                    count++;
                }
                l++;
            }
            k++;
        }
        return count;
    }


    public static String[][] createChess(String data) {
        String[] startWithW = "WBWBWBWB".split("");
        String[] startWithB = "BWBWBWBW".split("");

        String[][] chess = new String[8][8];
        if (data.equals("W")) {
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess[0].length; j++) {
                    if (i % 2 == 0) {
                        chess[i][j] = startWithW[j];
                    } else {
                        chess[i][j] = startWithB[j];
                    }
                }
            }
        } else {
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess[0].length; j++) {
                    if (i % 2 == 0) {
                        chess[i][j] = startWithB[j];
                    } else {
                        chess[i][j] = startWithW[j];
                    }
                }
            }
        }
        return chess;
    }
}
