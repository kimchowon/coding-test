package baekjoon.silver4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int[] loc1 = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            int r1 = Integer.parseInt(st.nextToken());

            int[] loc2 = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            int r2 = Integer.parseInt(st.nextToken());

            solution(loc1, r1, loc2, r2);
        }
    }

    public static void solution(int[] loc1, int r1, int[] loc2, int r2) {
        // 두 원의 중점 거리 (조규현, 백승환)
        double d = Math.sqrt(Math.pow(loc1[0] - loc2[0], 2) + Math.pow(loc1[1] - loc2[1], 2));

        int plus = r1 + r2;
        int minus;
        if (r1 >= r2) {
            minus = r1 - r2;
        } else {
            minus = r2 - r1;
        }

        // 두 점에서 만나는 경우
        if (minus < d && d < plus) {
            System.out.println(2);
            return;
        }

        // 한 점에서 만나는 경우
        if (plus == d || (minus == d && d != 0)) {
            System.out.println(1);
            return;
        }

        // 만나지 않는 경우
        if (plus < d || d < minus) {
            System.out.println(0);
            return;
        }

        // 무수히 많은 경우
        System.out.println(-1);
    }
}
