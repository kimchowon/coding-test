package baekjoon.silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Test04 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String king = st.nextToken();
        String stone = st.nextToken();
        int N = Integer.parseInt(st.nextToken());
        String[] directions = new String[N];

        for (int i = 0; i < N; i++) {
            directions[i] = br.readLine();
        }
        solution(king, stone, directions);
    }

    public static class Chess {
        int x;
        int y;

        public Chess(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void solution(String king, String stone, String[] directions) {

        Chess kingChess = createChess(king);
        Chess stoneChess = createChess(stone);

        HashMap<String, int[]> chessMap = createChessMap();

        for (int i = 0; i < directions.length; i++) {
            int[] kingDir = new int[2];
            int[] stoneDir = new int[2];

            int[] chessDir = chessMap.get(directions[i]);

            kingDir[0] = kingChess.x + chessDir[0];
            kingDir[1] = kingChess.y + chessDir[1];

            if (isExceedRangeOfChess(kingDir[0], kingDir[1])) {
                continue;
            }

            if (kingDir[0] == stoneChess.x && kingDir[1] == stoneChess.y) {
                stoneDir[0] = stoneChess.x + chessDir[0];
                stoneDir[1] = stoneChess.y + chessDir[1];

                if (isExceedRangeOfChess(stoneDir[0], stoneDir[1])) {
                    continue;
                }

                stoneChess.x = stoneDir[0];
                stoneChess.y = stoneDir[1];
            }
            kingChess.x = kingDir[0];
            kingChess.y = kingDir[1];
        }

        String kingDir = String.valueOf((char) (65 + kingChess.y)) + (8 - kingChess.x);
        String stoneDir = String.valueOf((char) (65 + stoneChess.y)) + (8 - stoneChess.x);

        System.out.println(kingDir);
        System.out.println(stoneDir);
    }

    public static Chess createChess(String chessStr) {
        String[] chessArray = chessStr.split("");
        int x = 8 - Integer.parseInt(chessArray[1]);
        int y = chessArray[0].charAt(0) - 65;
        Chess chess = new Chess(x, y);
        return chess;
    }

    public static boolean isExceedRangeOfChess(int x, int y) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return true;
        }
        return false;
    }

    public static HashMap<String, int[]> createChessMap() {
        HashMap<String, int[]> chessMap = new HashMap<>();
        chessMap.put("R", new int[]{0, 1});
        chessMap.put("L", new int[]{0, -1});
        chessMap.put("B", new int[]{1, 0});
        chessMap.put("T", new int[]{-1, 0});
        chessMap.put("RT", new int[]{-1, 1});
        chessMap.put("LT", new int[]{-1, -1});
        chessMap.put("RB", new int[]{1, 1});
        chessMap.put("LB", new int[]{1, -1});

        return chessMap;
    }
}
