package thisiscodingtest.binarysearch;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 기출문제02. 고정점 찾기
 * 제한시간: 20분 / 소요시간: 10분 9초
 */
public class Test04 {
    public static int[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        array = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int answer = binarySearch(0, array.length - 1);
        bw.write(answer + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static int binarySearch(int start, int end) {
        int answer = -1;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (array[mid] == mid) {
                answer = mid;
                break;
            }

            if (array[mid] < mid) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return answer;
    }
}
