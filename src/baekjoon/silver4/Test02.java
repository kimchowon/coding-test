package baekjoon.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Test02 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        solution(A);
    }

    public static class Data implements Comparable<Data> {
        int index;
        int value;

        public Data(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Data o) {
            return this.value - o.value;
        }
    }

    public static void solution(int[] A) {
        int[] P = new int[A.length];

        List<Data> dataList = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            Data data = new Data(i, A[i]);
            dataList.add(data);
        }

        Collections.sort(dataList);

        for (int i = 0; i < dataList.size(); i++) {
            Data data = dataList.get(i);
            int index = data.index;

            P[index] = i;
        }

        for (int p : P) {
            System.out.print(p + " ");
        }
    }
}
