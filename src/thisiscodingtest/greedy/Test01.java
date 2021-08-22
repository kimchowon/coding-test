package thisiscodingtest.greedy;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Test01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer> nums = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(nums, Collections.reverseOrder());
        int maxSum = solution(nums, M, K);
        bw.write(maxSum + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static int solution(List<Integer> nums, int M, int K) {
        int maxSum = 0;
        int count = 0;

        while (count < M) {
            for (int i = 1; i <= K; i++) {
                if (count < M) {
                    maxSum += nums.get(0);
                    count++;
                }
            }

            if (count < M) {
                maxSum += nums.get(1);
                count++;
            }
        }
        return maxSum;
    }
}
