package thisiscodingtest.binarysearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 실전문제01. 부품 찾기
 * 제한 시간: 30분 / 소요 시간: 10분 55초
 */
public class Test01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] nArray = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i < N; i++) {
            nArray[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] mArray = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i < M; i++) {
            mArray[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nArray);
        for (int i=0; i < M; i++) {
            int mNum = mArray[i];

            int l = 0;
            int r = nArray.length-1;

            boolean flag = false;

            while (l <= r) {
                int mid = (l + r) / 2;
                if (nArray[mid] == mNum) {
                    flag = true;
                    bw.write("yes ");
                    break;
                }

                if (nArray[mid] > mNum) {
                    r = mid -1;
                }else if (nArray[mid] < mNum) {
                    l = mid + 1;
                }
            }

            if (!flag) {
                bw.write("no ");
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
