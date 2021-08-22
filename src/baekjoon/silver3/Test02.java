package baekjoon.silver3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Test02 {

    public static class Planet {
        int x;
        int y;
        int r;

        public Planet(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] start = new int[2];
        int[] end = new int[2];

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());

            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());

            int planetNum = Integer.parseInt(br.readLine());
            List<Planet> planetList = new ArrayList<>();
            for (int j = 1; j <= planetNum; j++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                Planet planet = new Planet(x, y, r);
                planetList.add(planet);
            }

            solution(start, end, planetList);
        }
    }

    public static void solution(int[] start, int[] end, List<Planet> planetList) {

        int count = 0;
        for (int i=0; i < planetList.size(); i++) {
            Planet planet = planetList.get(i);
            int startDistance = (int) (Math.pow(start[0]-planet.x, 2) + Math.pow(start[1]-planet.y, 2));
            int endDistance = (int) (Math.pow(end[0]-planet.x, 2) + Math.pow(end[1]-planet.y, 2));

            int radius = (int) Math.pow(planet.r, 2);

            if (startDistance <= radius && endDistance <= radius) {
                continue;
            }

            if (startDistance <= radius || endDistance <= radius) {
                count++;
            }
        }
        System.out.println(count);
    }
}
