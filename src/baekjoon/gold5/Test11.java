package baekjoon.gold5;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 12865 - 평범한 배낭
 */
public class Test11 {
    public static int[][] dp;

    static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물품의 수
        int K = Integer.parseInt(st.nextToken()); // 최대 무게

        dp = new int[N + 1][K + 1];

        Item[] items = new Item[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            items[i] = new Item(w, v);
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (items[i].weight > j) { // 현재 아이템의 무게가 배낭에 넣을 수 있는 최대 무게보다 큼
                    dp[i][j] = dp[i - 1][j]; // 이전 dp값 넣기
                } else { // 현재 아이템의 무게가 배낭에 넣을 수 있는 최대 무게와 같거나 작음
                    int num1 = items[i].value + dp[i - 1][j - items[i].weight];
                    int num2 = dp[i - 1][j];
                    dp[i][j] = Math.max(num1, num2);
                }
            }
        }

        bw.write(dp[N][K] + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}
