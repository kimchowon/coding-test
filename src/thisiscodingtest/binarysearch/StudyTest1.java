package thisiscodingtest.binarysearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StudyTest1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] nArray = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nArray[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] mArray = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            mArray[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nArray);

        for (int m : mArray) {
            int start = 0;
            int end = nArray.length - 1;
            boolean flag = false;
            while (start <= end) {
                int mid = (start + end) / 2;

                if (nArray[mid] == m) {
                    flag = true;
                    bw.write(1 + "\n");
                    break;
                } else if (nArray[mid] > m) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            if (!flag) {
                bw.write(0 + "\n");
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void solution(int[] nArray, int[] mArray) {
    }
}
