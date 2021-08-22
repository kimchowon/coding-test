package thisiscodingtest.implement;

import java.io.*;

/**
 * 실전 문제. 왕실의 나이트
 * 제한시간: 20분 / 소요시간: 16분 31초
 */
public class Test03 {
    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] location = br.readLine().split(""); // a행 1열

        int y = Math.abs(65 - location[0].toUpperCase().charAt(0));
        int x = Integer.parseInt(location[1]) - 1;

        int[] xArray = {x - 2, x - 2, x + 2, x + 2, x - 1, x + 1, x - 1, x + 1};
        int[] yArray = {y + 1, y - 1, y + 1, y - 1, y + 2, y + 2, y - 2, y - 2};

        for (int i = 0; i < xArray.length; i++) {
            if (checkValidation(xArray[i], yArray[i])) {
                answer++;
            }
        }

        bw.write(answer + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    public static boolean checkValidation(int x, int y) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return false;
        }
        return true;
    }
}
