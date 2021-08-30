package kakao.blind2021.level3;

import java.util.Arrays;

/**
 *
 */
public class Test01 {
    public static void main(String[] args) {

       /* int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};*/

        int n = 7;
        int s = 3;
        int a = 4;
        int b = 1;
        int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};

        int answer = solution(n, s, a, b, fares);
        System.out.println(answer);
    }



    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int INFINITE = 100000 * n;
        int answer = 0;

        // 2차원 배열 생성
        int[][] nArray = new int[n + 1][n + 1];
        for (int i = 0; i < nArray.length; i++) {
            Arrays.fill(nArray[i], INFINITE);
            nArray[i][i] = 0;
        }

        for (int i = 0; i < fares.length; i++) {
            int[] fare = fares[i];
            int x = fare[0];
            int y = fare[1];
            int value = fare[2];

            nArray[x][y] = value;
            nArray[y][x] = value;
        }

        // 결과 그래프
        int[][] resultArray = new int[n + 1][n + 1];
        for (int i = 0; i < resultArray.length; i++) {
            for (int j = 0; j < resultArray[0].length; j++) {
                resultArray[i][j] = nArray[i][j];
            }
        }

        // k = 거쳐가는 노드
        for (int k = 1; k <= n; k++) {
            // i = 출발 노드
            for (int i = 1; i <= n; i++) {
                // j = 도착 노드
                for (int j = 1; j <= n; j++) {
                    if (resultArray[i][k] + resultArray[k][j] < resultArray[i][j]) {
                        resultArray[i][j] = resultArray[i][k] + resultArray[k][j];
                    }
                }
            }
        }

        int minFare = Integer.MAX_VALUE;
        // 각 지점을 합승 구간이라고 가정하고 택시요금을 계산해봄
        for (int i = 1; i <= n; i++) {
            int share = resultArray[s][i]; // 합승 구간 요금

            int aFare = resultArray[a][i]; // 합승 구간 -> a까지 요금
            int bFare = resultArray[b][i]; // 합승 구간 -> b까지 요금

            int sum = share + aFare + bFare;
            minFare = Math.min(minFare, sum);
        }

        return minFare;
    }
}
