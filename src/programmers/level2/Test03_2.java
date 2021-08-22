package programmers.level2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Test03_2 {
    /**
     * 메인 메소드
     * @param args
     */
    public static void main(String[] args) {
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        System.out.println("answer : " + Arrays.toString(solution(6, 4, picture)));
    }

    /**
     * solution 메소드
     * @param m
     * @param n
     * @param picture
     * @return
     */
    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (picture[i][j] == 0) {
                    continue;
                }

                int area = BFS(i, j, visited, picture, answer);
                maxSizeOfOneArea = maxSizeOfOneArea < area ? area : maxSizeOfOneArea;
            }
        }
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    /**
     * BFS 메소드
     * @param x
     * @param y
     * @param visited
     * @param picture
     * @param answer
     * @return
     */
    public static int BFS(int x, int y, boolean[][] visited, int[][] picture, int[] answer) {
        answer[0]++;
        answer[1] = 1;
        visited[x][y] = true;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});

        int[] check_x = {-1, 1, 0, 0};
        int[] check_y = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int[] cur_xy = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur_xy[0] + check_x[i];
                int ny = cur_xy[1] + check_y[i];

                if (nx < 0 || nx >= picture.length || ny < 0 || ny >= picture[0].length) {
                    continue;
                }
                if (picture[cur_xy[0]][cur_xy[1]] != picture[nx][ny]) {
                    continue;
                }
                if (visited[nx][ny] || picture[nx][ny] == 0) {
                    continue;
                }

                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                answer[1]++;
            }
        }
        return answer[1];
    }
}
