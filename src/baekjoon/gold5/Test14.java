package baekjoon.gold5;

import java.io.*;
import java.util.*;

public class Test14 {
    public static final int START_CHANNEL = 100; // 처음 위치한 채널
    public static int N; // 이동하려는 채널
    public static boolean[] isUsefulBtns = new boolean[10]; // 채널 클릭 가능 여부
    public static int tempChannel = 100; // 초기 가장 근접한 채널 초기화

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 이동하려는 채널
        int M = Integer.parseInt(br.readLine()); // 고장난 버튼 개수

        Arrays.fill(isUsefulBtns, true);
        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                int num = Integer.parseInt(st.nextToken());
                isUsefulBtns[num] = false; // 고장났으므로 false로 변경
            }
        }

        // 만약 이동하려는 채널이 처음채널과 그대로라면 이동할 필요 없으므로 바로 0리턴
        if (N == START_CHANNEL) {
            bw.write(0 + "\n");
        } else {
            // 1.오로지 +, -로만 이동하는 횟수
            int num1 = Math.abs(N - 100);

            // 2.원하는 가장 근접한 채널까지 이동한 후 +,-로 이동하는 횟수
            int end = String.valueOf(N).length();
            int start = end - 1 == 0 ? end : end - 1;
            for (int i = start; i <= end + 1; i++) {
                int[] buckets = new int[i];
                solution(isUsefulBtns, buckets, buckets.length);
            }
            int tempLen = String.valueOf(tempChannel).length();
            int num2 = tempLen + Math.abs(N - tempChannel);

            int answer = Math.min(num1, num2);
            bw.write(answer + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void solution(boolean[] isUsefulBtns, int[] buckets, int k) {
        if (k == 0) {
            String str = "";
            for (int i = 0; i < buckets.length; i++) {
                str += buckets[i];
            }
            int num = Integer.parseInt(str);
            int num1 = Math.abs(N - tempChannel);
            int num2 = Math.abs(N - num);
            tempChannel = num1 <= num2 ? tempChannel : num;
            return;
        }

        int lastIndex = buckets.length - k - 1;
        for (int i = 0; i < isUsefulBtns.length; i++) {
            if (isUsefulBtns[i] == true) {
                buckets[lastIndex + 1] = i;
                solution(isUsefulBtns, buckets, k - 1);
            }
        }
    }
}
