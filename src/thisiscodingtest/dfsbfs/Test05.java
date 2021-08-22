package thisiscodingtest.dfsbfs;

import java.io.*;
import java.util.*;

public class Test05 {
    public static int L;
    public static int R;
    public static int[][] lands;
    public static boolean[][] visited;
    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        lands = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                lands[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
        bw.write(answer + "\n");

       /* 2 20 50
        50 30
        20 40*/
        br.close();
        bw.flush();
        bw.close();
    }

    public static void solution() {
        int[] mX = {-1, +1, 0, 0}; // 상 하 좌 우
        int[] mY = {0, 0, -1, +1}; // 상 하 좌 우

        while (true) {
            boolean flag = false;
            for (int i = 0; i < lands.length; i++) {
                for (int j = 0; j < lands[i].length; j++) {
                    if (visited[i][j]) {
                        continue;
                    }

                    Queue<int[]> queue = new LinkedList<>();
                    List<int[]> landList = new ArrayList<>();
                    queue.add(new int[]{i, j});
                    landList.add(new int[]{i, j});
                    visited[i][j] = true;
                    int peopleNum = lands[i][j];
                    int cityNum = 1;
                    while (!queue.isEmpty()) {
                        int[] land = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int tX = land[0] + mX[k];
                            int tY = land[1] + mY[k];

                            if (checkValidation(tX, tY) && !visited[tX][tY]) {
                                int num = Math.abs(lands[land[0]][land[1]] - lands[tX][tY]);
                                if (L <= num && num <= R) {
                                    flag = true;
                                    peopleNum += lands[tX][tY];
                                    cityNum++;
                                    visited[tX][tY] = true;
                                    queue.add(new int[]{tX, tY});
                                    landList.add(new int[]{tX, tY});
                                }
                            }
                        }
                    }

                    int result = peopleNum / cityNum;
                    for (int k = 0; k < landList.size(); k++) {
                        int[] land = landList.get(k);
                        lands[land[0]][land[1]] = result;
                    }
                }
            }

            for (boolean[] v : visited) {
                Arrays.fill(v, false);
            }

            if (flag) {
                answer++;
            } else {
                break;
            }
        }
    }

    public static boolean checkValidation(int x, int y) {
        if (x < 0 || x >= lands.length || y < 0 || y >= lands[0].length) {
            return false;
        }
        return true;
    }
}
