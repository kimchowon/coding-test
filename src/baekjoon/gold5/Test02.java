package baekjoon.gold5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 14502 - 연구소
 */
public class Test02 {
    public static int N; // 행
    public static int M; // 열
    public static int[][] lab; // 연구소
    public static List<int[]> infectPlaceList = new ArrayList<>(); // 처음부터 감염된 칸들
    public static boolean[][] infected; // 감염이 전파된 여부
    public static int zeroCount = 0; // 빈칸의 개수
    public static int count = 0; // 감염이 전파된 칸의 개수
    public static int max = Integer.MIN_VALUE; // 정답

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];
        infected = new boolean[N][M];

        int num = N * M;
        int[] buckets = new int[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                lab[i][j] = n;

                if (n == 0) {
                    zeroCount++;
                }

                if (n == 2) {
                    int[] infectedPlace = {i, j};
                    infectPlaceList.add(infectedPlace);
                }
            }
        }

        getMaxOfSafeZone(num, buckets, buckets.length);
        bw.write(max + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    private static void getMaxOfSafeZone(int nums, int[] buckets, int k) {
        if (k == 0) {
            count = 0;
            for (int i = 0; i < buckets.length; i++) {
                int quo = buckets[i] / M; // 행
                int remain = buckets[i] % M; // 렬
                lab[quo][remain] = 1;
            }

            for (int[] infectedPlace : infectPlaceList) {
                getInfectedPlace(infectedPlace[0], infectedPlace[1]);
            }

            max = Math.max(max, zeroCount - count - 3);
            infected = new boolean[N][M];

            for (int i = 0; i < buckets.length; i++) {
                int quo = buckets[i] / M; // 행
                int remain = buckets[i] % M; // 렬
                lab[quo][remain] = 0;
            }
            return;
        }

        int lastIndex = buckets.length - k - 1;

        for (int i = 0; i < nums; i++) {
            int quo = i / M; // 행
            int remain = i % M; // 렬
            if (lab[quo][remain] == 0) {
                if (buckets.length == k) {
                    buckets[0] = i;
                    getMaxOfSafeZone(nums, buckets, k - 1);
                } else {
                    if (buckets[lastIndex] < i) {
                        buckets[lastIndex + 1] = i;
                        getMaxOfSafeZone(nums, buckets, k - 1);
                    }
                }
            }
        }
    }

    public static void getInfectedPlace(int x, int y) {
        infected[x][y] = true;

        // 위
        if (checkValidation(x - 1, y) && lab[x - 1][y] == 0 && !infected[x - 1][y]) {
            count++;
            getInfectedPlace(x - 1, y);
        }

        // 아래
        if (checkValidation(x + 1, y) && lab[x + 1][y] == 0 && !infected[x + 1][y]) {
            count++;
            getInfectedPlace(x + 1, y);
        }

        // 왼
        if (checkValidation(x, y - 1) && lab[x][y - 1] == 0 && !infected[x][y - 1]) {
            count++;
            getInfectedPlace(x, y - 1);
        }

        // 오
        if (checkValidation(x, y + 1) && lab[x][y + 1] == 0 && !infected[x][y + 1]) {
            count++;
            getInfectedPlace(x, y + 1);
        }
    }

    public static boolean checkValidation(int x, int y) {
        if (x < 0 || x >= lab.length || y < 0 || y >= lab[x].length) {
            return false;
        }
        return true;
    }
}
