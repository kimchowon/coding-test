package baekjoon.gold5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 15686 - 치킨
 */
public class Test08 {
    public static int N;
    public static int M;
    public static List<int[]> houses = new ArrayList<>();
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Integer> chickens = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());

                if (num == 2) {
                    chickens.add(count);
                } else if (num == 1) {
                    houses.add(new int[]{i, j});
                }
                count++;
            }
        }
        int[] buckets = new int[M];
        pickChickenStores(chickens, buckets, buckets.length);

        bw.write(min + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void pickChickenStores(List<Integer> chickens, int[] buckets, int k) {
        if (k == 0) {
            min = Math.min(min, getChickenDistance(buckets));
            return;
        }

        int lastIndex = buckets.length - k - 1;
        for (int i = 0; i < chickens.size(); i++) {
            if (buckets.length == k) {
                buckets[0] = chickens.get(i);
                pickChickenStores(chickens, buckets, k - 1);
            } else {
                if (buckets[lastIndex] < chickens.get(i)) {
                    buckets[lastIndex + 1] = chickens.get(i);
                    pickChickenStores(chickens, buckets, k - 1);
                }
            }
        }
    }

    public static int getChickenDistance(int[] chickens) {
        int cityDistance = 0;
        for (int i = 0; i < houses.size(); i++) {
            int min = Integer.MAX_VALUE;
            for (int chicken : chickens) {
                int quo = chicken / N;
                int remain = chicken % N;
                int[] house = houses.get(i);
                int distance = Math.abs(quo - house[0]) + Math.abs(remain - house[1]);
                min = Math.min(min, distance);
            }
            cityDistance += min;
        }
        return cityDistance;
    }
}
