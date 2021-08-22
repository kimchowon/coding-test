package thisiscodingtest.binarysearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 실전문제02. 떡볶이 떡 만들기
 * 제한시간: 40분 / 소요시간: 23분 12초
 */
public class Test02 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 떡의 개수
        int M = Integer.parseInt(st.nextToken()); // 요청한 떡의 총 길이

        int[] dduck = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i < N; i++) {
            dduck[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(dduck);

        int answer = Integer.MAX_VALUE;

        int max = dduck[dduck.length-1];
        int min = dduck[0];
        while (min <= max) {
            int H = (max + min) / 2;

            int sum = 0;
            for (int i=0; i < N; i++) {
                int n = dduck[i] - H;
                if (n >0) {
                    sum += n;
                }
            }

            if (sum == M) {
                answer = Math.min(answer, H);
                max = H-1;
            }else {
                if (sum > M) {
                    min = H+1;
                }else if (sum < M) {
                    max = H-1;
                }
            }
        }

        bw.write(answer + "\n");

        br.close();
        bw.flush();
        bw.close();
    }
}
