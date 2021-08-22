package baekjoon.gold5;

import java.io.*;

public class Test09 {
    public static int N;
    public static String[][] graph;
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        graph = new String[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String[] sections = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                graph[i][j] = sections[j];
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    count++;
                    getSection(i, j);
                }
            }
        }

        bw.write(count + " ");

        visited = new boolean[N][N];
        count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    count++;
                    getSection2(i, j);
                }
            }
        }

        bw.write(count + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void getSection(int x, int y) {
        visited[x][y] = true;

        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nX = x + dx[i];
            int nY = y + dy[i];

            if (checkValidation(nX, nY) && !visited[nX][nY]) {
                if (graph[x][y].equals(graph[nX][nY])) {
                    getSection(nX, nY);
                }
            }
        }
        return;
    }

    public static void getSection2(int x, int y) {
        visited[x][y] = true;

        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nX = x + dx[i];
            int nY = y + dy[i];

            if (checkValidation(nX, nY) && !visited[nX][nY]) {
                if (graph[x][y].equals(graph[nX][nY])
                        || (graph[x][y].equals("R") && graph[nX][nY].equals("G"))
                        || (graph[x][y].equals("G") && graph[nX][nY].equals("R"))) {
                    getSection2(nX, nY);
                }
            }
        }
        return;
    }

    public static boolean checkValidation(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N) {
            return false;
        }
        return true;
    }
}
