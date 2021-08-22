package thisiscodingtest.sort;

import java.io.*;
import java.util.Arrays;

public class StudyTest {
    static class Point implements Comparable<Point> {
        int num; // 숫자
        int idx; // 인덱스

        Point(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }

        @Override
        public int compareTo(Point o) {
            return num - o.num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 배열의 크기
        int[] array = new int[N+1];
        for (int i=1; i <=N; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

       /* boolean change;
        for (int i=1; i <=N+1; i++) {
            change = false;
            for (int j=1; j <=N-i; j++) {
                if (array[j] > array[j+1]) {
                    change = true;
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }

            if (change == false) {
                bw.write(i + "\n");
                break;
            }
        }*/

        Point[] points = new Point[N + 1];
        for (int i = 1; i <= N; i++) {
            int temp = Integer.parseInt(br.readLine());
            points[i] = new Point(temp, i);
        }
        Arrays.sort(points, 1, N + 1);

        int max = 0;
        for (int i = 1; i <= N; i++) {
        }

        bw.write((max + 1) + "\n");
        bw.close();
        br.close();


    }
}
