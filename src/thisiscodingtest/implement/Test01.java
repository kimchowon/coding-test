package thisiscodingtest.implement;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 예제 4-1. 상하좌우
 * 제한시간:15분 / 소요시간: 12분
 */
public class Test01 {
    public static int[][] places;
    public static int[] location = {1, 1}; // 시작 위치 초기화

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 공간의 크기
        places = new int[N + 1][N + 1]; // 공간들

        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            String direction = st.nextToken();
            solution(direction);
        }

        bw.write(location[0] + " " + location[1] + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void solution(String direction) {
        int[] moveLocation = getLocation(direction);

        if (checkValidation(moveLocation)) {
            location = moveLocation;
        }
    }

    public static int[] getLocation(String direction) {
        int[] moveLocation = new int[2];
        switch (direction) {
            case "L":
                moveLocation = new int[]{location[0], location[1] - 1};
                break;
            case "R":
                moveLocation = new int[]{location[0], location[1] + 1};
                break;
            case "U":
                moveLocation = new int[]{location[0] - 1, location[1]};
                break;
            case "D":
                moveLocation = new int[]{location[0] + 1, location[1]};
                break;
        }
        return moveLocation;
    }

    public static boolean checkValidation(int[] location) {
        if (location[0] <= 0 || location[0] >= places.length || location[1] <= 0 || location[1] >= places[0].length) {
            return false;
        }
        return true;
    }
}
