package baekjoon.gold4;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Test01 {
    public static List<Flower> flowerList = new ArrayList<>();

    static class Flower implements Comparable<Flower>{
        int startPeriod;
        int endPeriod;

        public Flower(int startPeriod, int endPeriod) {
            this.startPeriod = startPeriod;
            this.endPeriod = endPeriod;
        }

        @Override
        public int compareTo(Flower o) {
            return this.startPeriod >= o.startPeriod ? 1 : -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 꽃의 개수

        int[] dayPeriod = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 31, 30, 31};

        // 달배열 생성
        int[] monthPeriod = new int[13];
        monthPeriod[1] = 31;
        for (int i=2; i <= 12; i++) {
            monthPeriod[i] = monthPeriod[i-1] + dayPeriod[i];
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());

            int startPeriod = monthPeriod[startMonth] - (dayPeriod[startMonth] - startDay);
            int endPeriod = monthPeriod[endMonth] - (dayPeriod[endMonth] - endDay);
            Flower flower = new Flower(startPeriod, endPeriod);
            flowerList.add(flower);
        }

        Collections.sort(flowerList);

        br.close();
        bw.flush();
        bw.close();
    }

}
