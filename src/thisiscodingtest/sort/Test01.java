package thisiscodingtest.sort;

import java.io.*;

/**
 * 실전문제1. 위에서 아래로
 * 제한시간: 15분 / 소요시간: 8분 15초
 * 선택 정렬 알고리즘을 이용하여 풀이
 */
public class Test01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        for (int i=0; i < N; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        for (int i=0; i < array.length; i++) {
            int max = i;
            for (int j=i; j < array.length; j++) {
                if (array[j] > array[max]) {
                    max = j;
                }
            }
            int temp = array[i];
            array[i] = array[max];
            array[max] = temp;
        }

        for (int i=0; i < array.length; i++) {
            bw.write(array[i] + " ");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
