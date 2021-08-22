package thisiscodingtest.dfsbfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Test06 {
    public static int[][] zoo;
    public static int[][][] visited;
    public static int W;
    public static int H;
    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int K = Integer.parseInt(br.readLine()); // 말처럼 이동할 수 있는 횟수

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken()); // 가로 길이
        H = Integer.parseInt(st.nextToken()); // 세로 길이

        zoo = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                zoo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new int[K + 1][H][W];
        solution(0, 0, K);

        bw.write(answer + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static class CurInfo {
        int x;
        int y;
        int count;
        int k;

        public CurInfo(int x, int y, int count, int k) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.k = k;
        }
    }

    public static void solution(int x, int y, int K) {
        // 인접한 방향으로 이동
        int[] mX = {-1, +1, 0, 0};
        int[] mY = {0, 0, -1, +1};

        // 말처럼 이동
        int[] nX = {-2, -2, -1, +1, +2, +2, +1, -1};
        int[] nY = {+1, -1, +2, +2, +1, -1, -2, -2};

        visited[0][x][y] = 1; // K 사용횟수 0번일때 0,0 방문 표시
        Queue<CurInfo> queue = new LinkedList<>();
        queue.add(new CurInfo(x, y, 0, 0));

        while (!queue.isEmpty()) {
            CurInfo curInfo = queue.poll();

            if (curInfo.x == zoo.length - 1 && curInfo.y == zoo[0].length - 1) {
               answer = Math.min(answer, curInfo.count);
            }

            // 인접한 방향으로 움직일 때
            for (int i = 0; i < 4; i++) {
                int tX = curInfo.x + mX[i];
                int tY = curInfo.y + mY[i];

                if (checkValidation(tX, tY) && zoo[tX][tY] == 0 && visited[curInfo.k][tX][tY] == 0) {
                    visited[curInfo.k][tX][tY] = 1;
                    queue.add(new CurInfo(tX, tY, curInfo.count+1, curInfo.k));
                }
            }

            if (curInfo.k < K) {
                // 말의 방향으로 움직일 때
                for (int i = 0; i < 8; i++) {
                    int tX = curInfo.x + nX[i];
                    int tY = curInfo.y + nY[i];

                    if (checkValidation(tX, tY) && zoo[tX][tY] == 0 && visited[curInfo.k + 1][tX][tY] == 0) {
                        visited[curInfo.k + 1][tX][tY] = 1;
                        queue.add(new CurInfo(tX, tY, curInfo.count+1, curInfo.k + 1));
                    }
                }
            }
        }

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
    }

    public static boolean checkValidation(int x, int y) {
        if (x < 0 || x >= zoo.length || y < 0 || y >= zoo[0].length) {
            return false;
        }
        return true;
    }
}
