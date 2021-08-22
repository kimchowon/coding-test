package baekjoon.gold5;

import java.io.*;

/**
 * 9251 - LCS
 */
public class Test04 {
    public static int[][] LCS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String X = br.readLine();
        String Y = br.readLine();

        int xLen = X.length();
        int yLen = Y.length();

        LCS = new int[xLen + 1][yLen + 1];

        getLCS(X, Y);
        bw.write(LCS[xLen][yLen] + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void getLCS(String X, String Y) {
        for (int i = 1; i < LCS.length; i++) {
            for (int j = 1; j < LCS[0].length; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                } else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                }
            }
        }
    }
}
