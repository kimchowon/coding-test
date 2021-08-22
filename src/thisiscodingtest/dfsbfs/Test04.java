package thisiscodingtest.dfsbfs;

import java.io.*;
import java.util.*;

public class Test04 {
    public static int[][] exa;
    public static boolean[][] visited;
    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        exa = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                exa[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int second = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        solution(K, second, x, y);
        bw.write(answer + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    static class Virus implements Comparable<Virus> {
        int x;
        int y;
        int k;

        public Virus(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }

        @Override
        public int compareTo(Virus o) {
            return this.k >= o.k ? 1 : -1;
        }
    }

    public static void solution(int K, int second, int x, int y) {
        int count = 0;
        int mX[] = {-1, +1, 0, 0};
        int mY[] = {0, 0, -1, +1};

        while (count < second) {
            count++;

            PriorityQueue<Virus> queue = new PriorityQueue<>();
            for (int i = 1; i < exa.length; i++) {
                for (int j = 1; j < exa[i].length; j++) {
                    if (exa[i][j] > 0 && !visited[i][j]) {
                        queue.add(new Virus(i, j, exa[i][j]));
                        visited[i][j] = true;
                    }
                }
            }

            while (!queue.isEmpty()) {
                Virus virus = queue.poll();
                int vX = virus.x;
                int vY = virus.y;
                int vK = virus.k;

                for (int i = 0; i < 4; i++) {
                    int tX = vX + mX[i];
                    int tY = vY + mY[i];

                    if (checkValidation(tX, tY) && exa[tX][tY]==0) {
                        exa[tX][tY] = vK;
                    }
                }
            }
        }
        answer = exa[x][y];
    }

    public static boolean checkValidation(int x, int y) {
        if (x < 1 || x >= exa.length || y < 1 || y >= exa[0].length) {
            return false;
        }
        return true;
    }
}
