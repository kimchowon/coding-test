package thisiscodingtest.sort;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 실전3. 두 배열의 원소 교체
 * 제한시간: 20분 / 소요시간: 6분 38초
 */
public class Test03 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] array1 = new int[N];
        int[] array2 = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array2[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array1);
        Arrays.sort(array2);

        for (int i = 0; i < K; i++) {
            int temp = array1[i];
            array1[i] = array2[N - i - 1];
            array2[N - i - 1] = temp;
        }

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += array1[i];
        }

        bw.write(sum + "\n");

        br.close();
        bw.flush();
        bw.close();
    }
}
