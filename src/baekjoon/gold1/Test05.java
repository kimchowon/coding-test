package baekjoon.gold1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test05 {
    static int V;
    static int[][] array;
    static int[][] dp;
    static int INF = 16 * 1_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        V = Integer.parseInt(br.readLine()); // 도시의 수
        array = new int[V][V];
        dp = new int[V][(1 << V)-1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], INF);
        }
        for (int i = 0; i < array.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = tsp(0, 1);
        bw.write(answer + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static int tsp(int city, int visit) {
        // 모든 도시를 순회한 경우
        if (visit == (1 << V) - 1) {
            if (array[city][0] == 0) {
                return INF;
            } else {
                return array[city][0];
            }
        }

        // 이미 dp에 저장되어 있는 경우
        if (dp[city][visit] != INF) {
            return dp[city][visit];
        }

        for (int i = 0; i < V; i++) {
            int next = visit | (1 << i);

            // i번 노드에 대해서 길이 없거나 이미 방문한 도시일 때
            if (array[city][i] == 0 || (visit & (1 << i)) != 0) {
                continue;
            }

            dp[city][visit] = Math.min(dp[city][visit], tsp(i, next) + array[city][i]);
        }
        return dp[city][visit];
    }
}
