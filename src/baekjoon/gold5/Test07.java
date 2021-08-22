package baekjoon.gold5;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Test07 {
    public static int[][] graph;
    public static boolean[][] visited;
    public static int max = Integer.MIN_VALUE;
    public static int N;
    public static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
        bw.write(max + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    public static void solution() {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                getSum(i, j, 1, graph[i][j]);
                int sum = getSum2(i, j);
                max = Math.max(max, sum);
            }
        }
    }

    public static void getSum(int x, int y, int count, int sum) {
        if (count == 4) {
            max = Math.max(max, sum);
            return;
        }
        visited[x][y] = true;
        // 위
        if (checkValidation(x - 1, y) && !visited[x - 1][y]) {
            getSum(x - 1, y, count + 1, sum + graph[x - 1][y]);
        }
        // 아래
        if (checkValidation(x + 1, y) && !visited[x + 1][y]) {
            getSum(x + 1, y, count + 1, sum + graph[x + 1][y]);
        }
        // 왼쪽
        if (checkValidation(x, y - 1) && !visited[x][y - 1]) {
            getSum(x, y - 1, count + 1, sum + graph[x][y - 1]);
        }
        // 오른쪽
        if (checkValidation(x, y + 1) && !visited[x][y + 1]) {
            getSum(x, y + 1, count + 1, sum + graph[x][y + 1]);
        }
        visited[x][y] = false;
    }

    public static int getSum2(int x, int y) {
        int sum = graph[x][y];
        List<Integer> list = new ArrayList<>();
        // 위
        if (checkValidation(x - 1, y)) {
            list.add(graph[x - 1][y]);
        }
        // 아래
        if (checkValidation(x + 1, y)) {
            list.add(graph[x + 1][y]);
        }
        // 왼쪽
        if (checkValidation(x, y - 1)) {
            list.add(graph[x][y - 1]);
        }
        // 오른쪽
        if (checkValidation(x, y + 1)) {
            list.add(graph[x][y + 1]);
        }

        if (list.size() < 3) {
            return 0;
        }

        Collections.sort(list, Collections.reverseOrder());
        for (int i = 0; i < 3; i++) {
            sum += list.get(i);
        }
        return sum;
    }

    public static boolean checkValidation(int x, int y) {
        if (x < 0 || x >= graph.length || y < 0 || y >= graph[x].length) {
            return false;
        }
        return true;
    }
}
