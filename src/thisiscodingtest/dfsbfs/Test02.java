package thisiscodingtest.dfsbfs;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 실점문제02. 미로 탈출
 * 제한시간: 30분 / 소요시간: 18분 36초
 */
public class Test02 {
    public static int[][] miro;
    public static boolean[][] visited;
    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        miro = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String[] strings = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                miro[i][j] = Integer.parseInt(strings[j]);
            }
        }

        solution(0,0, 1);

        bw.write(answer + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    public static void solution(int x, int y, int count) {
        int mX[] = {-1, +1, 0, 0};
        int mY[] = {0, 0, -1, +1};

        if (x == miro.length-1 && y == miro[0].length-1) {
            answer = Math.min(answer, count);
            return;
        }

        visited[x][y] = true;

        for (int i=0; i < 4; i++) {
            int tX = x + mX[i];
            int tY = y + mY[i];

            if (!checkValidation(tX, tY)) {
                continue;
            }

            if (visited[tX][tY]) {
                continue;
            }

            if (miro[tX][tY]==0) {
                continue;
            }

            solution(tX, tY, count+1);
        }
    }

    public static boolean checkValidation(int x, int y) {
        if (x < 0 || x >= miro.length || y < 0 || y >= miro[0].length) {
            return false;
        }
        return true;
    }
}
