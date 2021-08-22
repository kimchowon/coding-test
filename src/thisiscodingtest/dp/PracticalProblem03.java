package thisiscodingtest.dp;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 효율적인 화폐 구성
 */
public class PracticalProblem03 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        // 초기화
        int[] mArray = new int[M + 1];
        Arrays.fill(mArray, Integer.MAX_VALUE);
        mArray[0] = 0;

        for (int i = 0; i < array.length; i++) { // 화폐 단위 하나씩 꺼냄
            for (int j = 1; j < mArray.length; j++) {
                if (j >= array[i] && mArray[j - array[i]] < Integer.MAX_VALUE) {
                    mArray[j] = Math.min(mArray[j], mArray[j - array[i]] + 1);
                }
            }
        }

        if (mArray[M] == Integer.MAX_VALUE) {
            bw.write(-1 + "\n");
        } else {
            bw.write(mArray[M] + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
