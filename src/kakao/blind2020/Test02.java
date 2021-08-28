package kakao.blind2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * level3. 기둥과 보 설치
 */
public class Test02 {
    public static void main(String[] args) {
        int n = 5;
        int[][] build_frame = {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};

       /* int n = 5;
        int[][] build_frame = {{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}};*/
        int[][] answer = solution(n, build_frame);
        for (int i = 0; i < answer.length; i++) {
            System.out.println(Arrays.toString(answer[i]));
        }
    }

    public static int N;
    public static int[][] pillars;
    public static int[][] furoshiki;

    public static int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};

        init(n); // 변수 및 배열 초기화

        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int kind = frame[2];
            int work = frame[3];

            if (work == 1) { // 설치
                installStructure(x, y, kind);
            } else { // 삭제
                removeStructure(x, y, kind);
            }
        }

        List<int[]> answerList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (pillars[i][j] == 0) {
                    answerList.add(new int[]{i, j, 0});
                }
                if (furoshiki[i][j] == 1) {
                    answerList.add(new int[]{i, j, 1});
                }
            }
        }

        answer = new int[answerList.size()][3];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        Arrays.sort(answer, (o1, o2) -> {
            int result = Integer.compare(o1[0], o2[0]);
            if (result == 0) {
                result = Integer.compare(o1[1], o2[1]);
                if (result == 0) {
                    result = Integer.compare(o1[2], o2[2]);
                }
            }
            return result;
        });
        return answer;
    }

    public static void init(int n) {
        N = n;
        pillars = new int[N + 1][N + 1];
        furoshiki = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                Arrays.fill(pillars[i], -2);
                Arrays.fill(furoshiki[i], -2);
            }
        }
    }

    public static void installStructure(int x, int y, int kind) {
        if (kind == 0 && isValidInstallPillar(x, y)) { // 기둥
            pillars[x][y] = 0;
        } else if (kind == 1 && isValidInstallFuroshiki(x, y)) { // 보
            furoshiki[x][y] = 1;
        }
    }

    // 기둥을 설치할 조건이 되는지 여부 체크
    // 기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
    public static boolean isValidInstallPillar(int x, int y) {
        // 기둥이 바닥위에 있는지 체크
        if (y == 0) {
            return true;
        }

        // 보의 한쪽 끝 부분 위에 있는지 체크
        if ((isValidCoordinate(x - 1, y) && furoshiki[x - 1][y] == 1) || (isValidCoordinate(x, y) && furoshiki[x][y] == 1)) {
            return true;
        }

        // 또 다른 기둥 위에 있는지 체크
        if (isValidCoordinate(x, y-1) && pillars[x][y-1] == 0) {
            return true;
        }

        return false;
    }

    // 보를 설치할 조건이 되는지 여부 체크
    // 보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
    public static boolean isValidInstallFuroshiki(int x, int y) {
        // 한쪽 끝 부분이 기둥 위에 있는지 체크
        if ((isValidCoordinate(x, y - 1) && pillars[x][y - 1] == 0) || (isValidCoordinate(x + 1, y - 1) && pillars[x + 1][y - 1] == 0)) {
            return true;
        }

        // 양쪽 끝 부분이 다른 보와 동시에 연결되어 있는지 체크
        if (isValidCoordinate(x - 1, y) && furoshiki[x - 1][y] == 1 && isValidCoordinate(x + 1, y) && furoshiki[x + 1][y] == 1) {
            return true;
        }

        return false;
    }

    public static boolean isValidCoordinate(int x, int y) {
        if (x < 0 || x > N || y < 0 || y > N) {
            return false;
        }
        return true;
    }

    public static void removeStructure(int x, int y, int kind) {
        int originValue = 0;
        if (kind == 0) { // 기둥
            originValue = pillars[x][y];
            pillars[x][y] = -2;
        } else if (kind == 1) { // 보
            originValue = furoshiki[x][y];
            furoshiki[x][y] = -2;
        }

        boolean flag = true;
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (pillars[i][j] == 0 && !isValidInstallPillar(i, j)) {
                    flag = false;
                    break;
                }

                if (furoshiki[i][j] == 1 && !isValidInstallFuroshiki(i, j)) {
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                if (kind == 0) { // 기둥
                    pillars[x][y] = originValue;
                } else if (kind == 1) { // 보
                    furoshiki[x][y] = originValue;
                }
            }
        }
    }
}
