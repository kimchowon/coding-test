package baekjoon.silver5;

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

        List<Integer> divisorList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            divisorList.add(Integer.parseInt(st.nextToken()));
        }
        solution(divisorList);
    }

    public static void solution(List<Integer> divisorList) {
        Collections.sort(divisorList);

        int min = divisorList.get(0);
        int max = divisorList.get(divisorList.size() - 1);

        int answer = min * max;
        System.out.println(answer);
    }
}
