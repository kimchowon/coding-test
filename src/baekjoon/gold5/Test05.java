package baekjoon.gold5;

import java.io.*;
import java.util.*;

/**
 * 14503 - 로봇 청소기
 */
public class Test05 {
    public static int[][] location;
    public static boolean[][] visited;
    public static int count = 0;

    public static class Change {
        int[] changeLoc;
        int changeDir;

        public Change(int[] changeLoc, int changeDir) {
            this.changeLoc = changeLoc;
            this.changeDir = changeDir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 세로
        int M = Integer.parseInt(st.nextToken()); // 가로
        location = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int[] curLoc = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        int curDir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                location[i][j] = Integer.parseInt(st.nextToken());

                if (location[i][j]==1) {
                    visited[i][j] = true;
                }
            }
        }
        getCleaningPlaceCount(curLoc, curDir);
        bw.write(count + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void getCleaningPlaceCount(int[] curLoc, int curDir) {

        while (true) {
            int x = curLoc[0];
            int y = curLoc[1];

            // 비어있는 칸이고 아직 청소하지 않았다면
            if (location[x][y] == 0 && !visited[x][y]) {
                count++;
                visited[x][y] = true;
            }

            // 4방향 모두 벽이라면
            if (((location[x][y-1]==1) && (location[x][y+1]==1) && (location[x-1][y]==1) && (location[x+1][y]==1))
            || (visited[x][y-1] && visited[x][y+1] && visited[x+1][y] && visited[x-1][y])) {
                int[] backDir = getBackDir(x, y, curDir);

                // 뒤 쪽 방향도 벽이면
                if (location[backDir[0]][backDir[1]]==1) {
                    break;
                }else {
                    curLoc[0] = backDir[0];
                    curLoc[1] = backDir[1];
                    continue;
                }
            }

            // 현재 위치에 따른 왼쪽 방향 설정
            Change change = changeDir(curLoc[0], curLoc[1], curDir);
            int cX = change.changeLoc[0];
            int cY = change.changeLoc[1];
            if (checkValidation(cX, cY) && !visited[cX][cY] && location[cX][cY]==0) {
                curDir = change.changeDir;
                curLoc[0] = cX;
                curLoc[1] = cY;
                continue;
            }else {
                curDir = change.changeDir;
                continue;
            }
        }
    }

    public static boolean checkValidation(int x, int y) {
        if (x < 0 || x >= location.length || y < 0 || y >= location[0].length) {
            return false;
        }
        return true;
    }

    public static Change changeDir(int x, int y, int curDir) {
        Change change = null;
        switch (curDir) {
            case 0: // 북일때
                // 북 -> 서
                change = new Change(new int[]{x, y - 1}, 3);
                break;
            case 1: // 동일때
                // 동 -> 북
                change = new Change(new int[]{x - 1, y}, 0);
                break;
            case 2: // 남일때
                // 남 -> 동
                change = new Change(new int[]{x, y + 1}, 1);
                break;
            case 3: // 서일때
                // 서 -> 남
                change = new Change(new int[]{x+1, y}, 2);
                break;
        }
        return change;
    }

    // 방향에 따른 한칸 후진
    public static int[] getBackDir(int x, int y, int curDir) {
        int[] backDir = new int[0];
        switch (curDir) {
            case 0:
                backDir = new int[]{x+1, y};
                break;
            case 1:
                backDir = new int[]{x, y-1};
                break;
            case 2:
                backDir = new int[]{x-1, y};
                break;
            case 3:
                backDir = new int[]{x, y+1};
                break;
        }
        return backDir;
    }
}
