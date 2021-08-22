package thisiscodingtest.binarysearch;

import java.io.*;
import java.util.*;

public class StudyTest2 {
    public static int num1 = 0;
    public static int num2 = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

/*        5
       -99 -2 -1 4 98*/

        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        solution(array);

        bw.write(num1 + " " + num2);

        br.close();
        bw.flush();
        bw.close();
    }

    public static void solution(int[] array) {
        int i, j;
        int min = Integer.MAX_VALUE;
        for (i = 0; i < array.length - 1; i++) {
            int closetZero = 0;
            for (j = i + 1; j < array.length; j++) {
                int sum = array[i] + array[j];
                if (j == i + 1) {
                    closetZero = sum;
                } else if (Math.abs(sum) < Math.abs(closetZero)) {
                    continue;
                } else {
                    break;
                }
            }
            if (array[i] + array[j - 1] == 0) {
                num1 = array[i];
                num2 = array[j - 1];
                break;
            }
            if ((array[i] + array[j - 1]) < min) {
                num1 = array[i];
                num2 = array[j - 1];
            }
            min = Math.min(min, (array[i] + array[j - 1]));
        }
    }
}
