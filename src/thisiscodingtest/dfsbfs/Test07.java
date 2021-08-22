package thisiscodingtest.dfsbfs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 기출20. 감시 피하기
 * 제한시간: 60분 // 소요시간: 1시간 4분 43초
 */
public class Test07 {
    public static String[][] hallway;
    public static List<int[]> teacherList = new ArrayList<>();
    public static boolean answer = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        hallway = new String[N][N];

        int[] items = new int[N * N];
        boolean[] isPossible = new boolean[N * N];
        int count = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                hallway[i][j] = st.nextToken();
                items[count] = count;

                if (hallway[i][j].equals("X")) {
                    isPossible[count] = true;
                }

                if (hallway[i][j].equals("T")) {
                    teacherList.add(new int[]{i, j});
                }
                count++;
            }
        }

        combination(items, isPossible, new int[3], 3);
        if (answer) {
            bw.write("YES\n");
        } else {
            bw.write("NO\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static int[] mX = {-1, +1, 0, 0};
    public static int[] mY = {0, 0, -1, +1};

    public static void combination(int[] items, boolean[] isPossible, int[] buckets, int k) {
        if (answer) {
            return;
        }
        if (k == 0) {
            for (int i = 0; i < buckets.length; i++) {
                int x = buckets[i] / hallway.length;
                int y = buckets[i] % hallway.length;
                hallway[x][y] = "O";
            }

            boolean check = checkTeacherList();
            if (check) {
                answer = true;
            }

            for (int i = 0; i < buckets.length; i++) {
                int x = buckets[i] / hallway.length;
                int y = buckets[i] % hallway.length;
                hallway[x][y] = "X";
            }
            return;
        }

        int lastIndex = buckets.length - k - 1;
        for (int i = 0; i < items.length; i++) {
            if (isPossible[i]) {
                if (buckets.length == k) {
                    buckets[0] = items[i];
                    combination(items, isPossible, buckets, k - 1);
                } else {
                    if (buckets[lastIndex] < items[i]) {
                        buckets[lastIndex + 1] = items[i];
                        combination(items, isPossible, buckets, k - 1);
                    }
                }
            }
        }
    }

    public static boolean checkValidation(int x, int y) {
        if (x < 0 || x >= hallway.length || y < 0 || y >= hallway[0].length) {
            return false;
        }
        return true;
    }

    public static boolean checkTeacherList() {
        for (int i = 0; i < teacherList.size(); i++) {
            int[] teacher = teacherList.get(i);
            for (int j = 0; j < 4; j++) {
                int tX = teacher[0] + mX[j];
                int tY = teacher[1] + mY[j];

                while (checkValidation(tX, tY)) {
                    if (hallway[tX][tY].equals("O")) {
                        break;
                    }
                    if (hallway[tX][tY].equals("S")) {
                        return false;
                    }
                    tX += mX[j];
                    tY += mY[j];
                }
            }
        }
        return true;
    }
}
