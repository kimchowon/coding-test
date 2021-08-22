package thisiscodingtest.implement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 11. 뱀
 */
public class Test09 {
    static class TimeInfo {
        int second;
        String dir;

        public TimeInfo(int second, String dir) {
            this.second = second;
            this.dir = dir;
        }
    }

    public static int[][] board;
    public static List<TimeInfo> timeInfoList = new ArrayList<>();
    public static List<int[]> snakes = new ArrayList<>();
    public static int curDir = 0; // 맨처음 방향 : 오른쪽 -> 오른쪽:0, 왼쪽:1, 위쪽:2, 아래쪽:3

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 보드크기
        board = new int[N + 1][N + 1];

        int K = Integer.parseInt(br.readLine()); // 사과개수
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            board[col][row] = 2; // 사과는 2로 설정
        }

        int M = Integer.parseInt(br.readLine()); // 시간 및 방향 정보
        for (int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int second = Integer.parseInt(st.nextToken()); // 초
            String dir = st.nextToken();
            timeInfoList.add(new TimeInfo(second, dir));
        }

        snakes.add(new int[]{1, 1}); // 맨처음 뱀의 위치
        int answer = solution();

        bw.write(answer + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    public static int solution() {
        int second = 0;
        while (true) {
            second++;

            int[] curLocation = snakes.get(snakes.size() - 1); // 뱀의 현재 위치중 머리
            int[] nextLocation = getNextLocation(curLocation[0], curLocation[1], curDir);

            if (!checkValidation(nextLocation)) { // 만약 벽에 부딪히면 게임 종료
                break;
            }

            boolean flag = true;
            for (int[] body : snakes) {
                if (body[0] == nextLocation[0] && body[1] == nextLocation[1]) {
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                break;
            }

            snakes.add(nextLocation); // 머리를 다음 칸으로 옮김
            if (board[nextLocation[0]][nextLocation[1]] == 2) { // 만약 다음 위치에 사과가 없으면
                board[nextLocation[0]][nextLocation[1]] = 0; // 사과를 먹으면 사과가 없어지므로 0이 됨!!!!!!! <-- 놓친 부분
            }else {
                snakes.remove(0); // 현재 꼬리 있는곳에서 꼬리를 땜
            }

            // 방향이 바뀌는 시간에 다다르면
            for (TimeInfo time : timeInfoList) {
                if (time.second == second) {
                    changeDir(time.dir);
                    break;
                }
            }
        }
        return second;
    }

    public static int[] getNextLocation(int x, int y, int dir) {
        int[] nextLocation = new int[2];
        switch (dir) {
            case 0:
                nextLocation = new int[]{x, y + 1};
                break;
            case 1:
                nextLocation = new int[]{x, y - 1};
                break;
            case 2:
                nextLocation = new int[]{x - 1, y};
                break;
            case 3:
                nextLocation = new int[]{x + 1, y};
                break;
        }
        return nextLocation;
    }

    public static boolean checkValidation(int[] location) {
        int x = location[0];
        int y = location[1];
        if (x < 1 || x >= board.length || y < 1 || y >= board[0].length) {
            return false;
        }
        return true;
    }

    public static void changeDir(String LD) {
        int[] lDir = {2, 3, 1, 0};
        int[] rDir = {3, 2, 0, 1};
        if (LD.equals("L")) {
            curDir = lDir[curDir];
        } else if (LD.equals("D")) {
            curDir = rDir[curDir];
        }
    }
}
