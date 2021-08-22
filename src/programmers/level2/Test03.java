package programmers.level2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Test03 {
    public static void main(String[] args) {
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        System.out.println("answer : " + Arrays.toString(solution(6, 0, picture)));

    }

    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        boolean[][] visited = new boolean[picture.length][picture[0].length];
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                if (visited[i][j] == true || picture[i][j] == 0) {
                    continue;
                }

                // BFS 수행
                int area = BFS(visited, picture, answer, i, j);
                maxSizeOfOneArea = maxSizeOfOneArea < area ? area : maxSizeOfOneArea;
            }
        }
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public static int BFS(boolean[][] visited, int[][] picture, int[] answer, int x, int y) {
        answer[0]++;
        answer[1] = 1;
        visited[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});

        int nx, ny;
        int[] x_array = {-1, 1, 0, 0};
        int[] y_array = {0, 0, -1, 1};
        while (!q.isEmpty()) {
            int[] array = q.poll();

            for (int i = 0; i < 4; i++) {
                nx = array[0] + x_array[i];
                ny = array[1] + y_array[i];

                if (0 > nx || nx >= picture.length || 0 > ny || ny >= picture[0].length) { continue; }
                if (visited[nx][ny] || picture[nx][ny] == 0) { continue; }

                if (picture[array[0]][array[1]] == picture[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                    answer[1]++;
                }

            }
        }
        return answer[1];
    }
}
