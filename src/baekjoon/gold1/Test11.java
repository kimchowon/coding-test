package baekjoon.gold1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 참고: https://blog.naver.com/occidere/220808155184
public class Test11 {
    public static String[][] maze;
    public static int[] start;
    public static int[][] count;
    public static int[] keys = new int[6];
    public static PriorityQueue<Integer> answers = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        maze = new String[N][M];
        count = new int[N][M];
        start = new int[2]; // 출발 좌표

        for (int i = 0; i < N; i++) {
            String[] strings = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                maze[i][j] = strings[j];

                if (maze[i][j].equals("0")) {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        solution();

        int min = answers.poll();
        System.out.println(min);

        br.close();
        bw.flush();
        bw.close();
    }

    public static void solution() {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(start);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int x = current[0];
            int y = current[1];

            if (maze[x][y].equals("1")) {
                answers.add(count[x][y]);
            }

            // 상
            if (isContainMaze(x - 1, y)) {
                if (!maze[x - 1][y].equals("#") && count[x-1][y]==0) {
                    count[x - 1][y] = count[x][y] + 1;
                    queue.add(new int[]{x-1, y});
                }
            }

            // 하
            if (isContainMaze(x + 1, y)) {
                if (!maze[x + 1][y].equals("#") && count[x+1][y]==0) {
                    count[x + 1][y] = count[x][y] + 1;
                    queue.add(new int[]{x+1, y});
                }
            }

            // 좌
            if (isContainMaze(x, y - 1)) {
                if (!maze[x][y - 1].equals("#") && count[x][y-1]==0) {
                    count[x][y - 1] = count[x][y] + 1;
                    queue.add(new int[]{x, y-1});
                }
            }

            // 우
            if (isContainMaze(x, y + 1)) {
                if (!maze[x][y + 1].equals("#") && count[x][y+1]==0) {
                    count[x][y + 1] = count[x][y] + 1;
                    queue.add(new int[]{x, y+1});
                }
            }
        }
    }

    public static boolean isContainMaze(int x, int y) {
        if (x < 0 || x >= maze.length) {
            return false;
        }

        if (y < 0 || y >= maze[0].length) {
            return false;
        }
        return true;
    }
}
