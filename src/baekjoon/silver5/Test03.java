package baekjoon.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Test03 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> sList = new ArrayList<>();
        while (st.hasMoreTokens()) {
            sList.add(Integer.parseInt(st.nextToken()));
        }
        int n = Integer.parseInt(br.readLine());
        solution(sList, n);
    }

    public static void solution(List<Integer> sList, int n) {
        if (!sList.contains(1)) {
            sList.add(0);
        }

        if (sList.contains(n)) {
            System.out.println(0);
            return;
        }

        Collections.sort(sList);

        int min = 0;
        int max = sList.size() - 1;
        while (min + 1 < max) {
            int mid = (min + max) / 2;
            if (sList.get(mid) < n) {
                min = mid;
            } else {
                max = mid;
            }
        }

        int num1 = (n - sList.get(min) - 1);
        int num2 = (sList.get(max) - n - 1);
        int answer = num1 + num2 + (num1 * num2);
        System.out.println(answer);
    }
}
