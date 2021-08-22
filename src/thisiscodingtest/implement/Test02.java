package thisiscodingtest.implement;

import java.io.*;

/**
 * 예제 4-2. 시각
 * 제한시간: 15분 / 소요시간: 10분 57초
 */
public class Test02 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 시간

        int hour = 0; // 시
        int minute = 0; // 분
        int second = 0; // 초

        int answer = 0;
        while (hour != N || minute != 59 || second != 59) {
            if (isContainsTree(hour, minute, second)) {
                answer++;
            }

            second++;
            if (second == 60) {
                minute++;
                second = 0;
            }

            if (minute == 60) {
                hour++;
                minute = 0;
            }
        }

        bw.write(answer + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    public static boolean isContainsTree(int hour, int minute, int second) {
        if (String.valueOf(hour).contains("3") || String.valueOf(minute).contains("3") || String.valueOf(second).contains("3")) {
            return true;
        }
        return false;
    }
}
