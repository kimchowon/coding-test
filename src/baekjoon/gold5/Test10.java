package baekjoon.gold5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Test10 {
    public static int[][] map;
    public static int[] directions;
    public static int[] dice; // 주사위면을 저장하는 배열
    public static List<Integer> answers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 지도 세로 크기
        int M = Integer.parseInt(st.nextToken()); // 지도 가로 크기
        int x = Integer.parseInt(st.nextToken()); // 주사위 최초 x 위치 좌표
        int y = Integer.parseInt(st.nextToken()); // 주사위 최초 y 위치 좌표
        int K = Integer.parseInt(st.nextToken()); // 명령 개수

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        directions = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            directions[i] = Integer.parseInt(st.nextToken());
        }

        dice = new int[6];
        answers = new ArrayList<>(); // 주사위 상단 수를 저장하는 리스트
        solution(x, y);

        for (int a : answers) {
            bw.write(a + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void solution(int x, int y) {
        for (int i = 0; i < directions.length; i++) {
            // 방향대로 지도 이동
            int[] location = moveMap(directions[i], x, y);
            if (checkValidation(location[0], location[1])) {

                x = location[0]; // 이동한 x 좌표
                y = location[1]; // 이동한 y 좌표

                rollTheDice(directions[i]); // 주사위를 굴림

                if (map[x][y] == 0) { // 이동한 칸에 쓰여 있는 수가 0이면
                    map[x][y] = dice[5]; // 주사위의 바닥면에 쓰여 있는 수가 칸에 복사

                } else { // 0이 아닌 경우
                    dice[5] = map[x][y]; // 주사위의 바닥면에 칸에 쓰여있는 수가 복사
                    map[x][y] = 0; // 칸에 쓰여있는 수는 0이 됨
                }
                answers.add(dice[0]);
            }
        }
    }

    // 주사위를 굴림
    public static void rollTheDice(int dir) {
        int[] change = new int[6];
        int[] temp = new int[6];

        switch (dir) {
            case 1: // 동쪽
                change = new int[]{2, 1, 5, 0, 4, 3};
                break;
            case 2: // 서쪽
                change = new int[]{3, 1, 0, 5, 4, 2};
                break;
            case 3: // 북쪽
                change = new int[]{1, 5, 2, 3, 0, 4};
                break;
            case 4: // 남쪽
                change = new int[]{4, 0, 2, 3, 5, 1};
                break;
        }

        for (int i = 0; i < 6; i++) {
            temp[change[i]] = dice[i];
        }

        for (int i = 0; i < 6; i++) {
            dice[i] = temp[i];
        }
    }

    public static int[] moveMap(int dir, int x, int y) {
        int[] location = new int[2];
        switch (dir) {
            case 1: // 동쪽
                location = new int[]{x, y + 1};
                break;
            case 2: // 서쪽
                location = new int[]{x, y - 1};
                break;
            case 3: // 북쪽
                location = new int[]{x - 1, y};
                break;
            case 4: // 남쪽
                location = new int[]{x + 1, y};
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
