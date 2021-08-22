package thisiscodingtest.dfsbfs;

import java.io.*;
import java.util.*;

public class Test01 {

    public static int[][] iceFrame;
    public static boolean[][] visited;
    public static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        iceFrame = new int[N][M];
        visited = new boolean[N][M];

        for (int i=0; i < N; i++) {
            String[] strings = br.readLine().split("");

            for (int j=0; j < strings.length; j++) {
                iceFrame[i][j] = Integer.parseInt(strings[j]);
            }
        }

        solution(0, 0);
        bw.write(answer + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void solution(int x, int y) {
        visited[x][y] = true;
        int[] mX = {-1, +1, 0, 0};
        int[] mY = {0,0, -1, +1};

        boolean flag = true;
        for (int i=0; i < 4; i++) {
            int tX = x + mX[i];
            int tY = y + mY[i];

            if (!checkValidation(tX, tY) || iceFrame[tX][tY]==1 || visited[tX][tY]) {
                flag = false;
                continue;
            }

            flag = true;
            solution(tX, tY);
        }

        if (!flag) {
            answer++;
        }
    }

    public static boolean checkValidation(int x, int y) {
        if (x < 0 || x >= iceFrame.length || y < 0 || y >= iceFrame[0].length) {
            return false;
        }
        return true;
    }
}
