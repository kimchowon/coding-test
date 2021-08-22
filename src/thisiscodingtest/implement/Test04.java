package thisiscodingtest.implement;

import java.io.*;
import java.util.*;

/**
 * 실전문제. 게임 개발
 * 제한시간: 40분 / 소요시간: 41분 27초
 */
public class Test04 {
    public static int[][] map;
    public static boolean[][] visited;
    public static int answer = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 맵 세로 크기
        int M = Integer.parseInt(st.nextToken()); // 맵 가로 크기
        map = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        for (int i=0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(x,y,dir);

        bw.write(answer + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    static class Status {
        int x;
        int y;
        int dir;

        public Status(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static void solution(int x, int y, int dir) {
        int[] dirX = {-1, +1, 0, 0};
        int[] dirY = {0, 0, -1, +1};

        while (true) {
            visited[x][y] = true;
            // 3단계. 만약 네방향 모두 이미 가본 칸이거나, 바다로 되어 있는 칸인지 체크
            boolean flag = true;
            for (int i=0; i < dirX.length; i++) {
                int tempX = x + dirX[i];
                int tempY = y + dirY[i];
                if (checkValidation(tempX, tempY)) {
                    if (!visited[tempX][tempY] && map[tempX][tempY]==0) {
                        flag = false;
                        break;
                    }
                }
            }

            // 3단계가 맞다면
            if (flag) {
                int[] backLocation = backLocation(x, y, dir);

                if (map[backLocation[0]][backLocation[1]]==1) {
                    break;
                }

                x = backLocation[0];
                y = backLocation[1];
                continue;
            }

            // 1단계. 현재 위치에서 현재 방향을 기준으로 왼쪽방향 정함
            Status status = getLeftLocation(x, y, dir);

            // 2단계
            if (checkValidation(status.x, status.y)) {
                if (!visited[status.x][status.y] && map[status.x][status.y]==0) { // 왼쪽 칸이 아직 가보지 않은 칸이면, 그리고 육지라면
                    // 방향 이동
                    dir = status.dir;
                    // 칸 이동
                    x = status.x;
                    y = status.y;

                    answer++;
                }else { // 이미 방문한 칸이었다면
                    // 방향만 이동
                    dir = status.dir;
                    continue; // 다시 1단계로 이동
                }
            }

        }
    }

    public static Status getLeftLocation(int x, int y, int dir) {
        Status status = null;
        switch (dir) {
            case 0: // 북쪽
                status = new Status(x, y-1, 3);
                break;
            case 1:
                status = new Status(x-1, y, 0);
                break;
            case 2:
                status = new Status(x, y+1, 1);
                break;
            case 3:
                status = new Status(x+1, y, 2);
                break;
        }
        return status;
    }

    public static int[] backLocation(int x, int y, int dir) {
        int[] location = new int[2];
        switch (dir) {
            case 0: // 북쪽
                location = new int[]{x+1, y};
                break;
            case 1: // 동쪽
                location = new int[]{x, y-1};
                break;
            case 2: // 남쪽
                location = new int[]{x+1, y};
                break;
            case 3: // 서쪽
                location = new int[]{x, y+1};
                break;
        }
        return location;
    }

    public static boolean checkValidation(int x, int y) {
        if (x < 0 || x >= map.length || y < 0 || y >= map[0].length) {
            return false;
        }
        return true;
    }
}
