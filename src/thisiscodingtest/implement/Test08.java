package thisiscodingtest.implement;

import java.io.IOException;

/**
 * 10. 자물쇠와 열쇠
 */
public class Test08 {

    public static void main(String[] args) throws IOException {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

        System.out.println(solution(key, lock));
    }

    public static int[][] place;

    public static boolean solution(int[][] key, int[][] lock) {
        int N = lock.length + (2 * (key.length - 1));
        place = new int[N][N];

        for (int i = 0; i <= N - key.length; i++) {
            for (int j = 0; j <= N - key.length; j++) {

                for (int k = 0; k < 4; k++) {
                    place = new int[N][N];
                    initPlace(key.length - 1, lock);
                    rotateKey(i, j, key, k);

                    boolean check = checkPlace(key.length - 1, lock);
                    if (check) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean checkPlace(int index, int[][] lock) {
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock[i].length; j++) {
                if (place[index + i][index + j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void rotateKey(int x, int y, int[][] key, int k) {
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[i].length; j++) {
                if (k == 0) {
                    place[x + i][y + j] += key[i][j];
                } else if (k == 1) {
                    place[x + i][y + j] += key[key.length - j - 1][i];
                } else if (k == 2) {
                    place[x + i][y + j] += key[key.length - i - 1][key.length - j - 1];
                } else if (k == 3) {
                    place[x + i][y + j] += key[j][key.length - i - 1];
                }
            }
        }
    }

    private static void initPlace(int index, int[][] lock) {
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock[i].length; j++) {
                place[index + i][index + j] = lock[i][j];
            }
        }
    }
}
