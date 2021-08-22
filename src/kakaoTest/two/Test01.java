package kakaoTest.two;

import java.util.Arrays;

/**
 * 2018 KAKAO BLIND RECRUITMENT
 * [1차] 프렌즈4블록
 * 소요시간: 1시간 28분
 */
public class Test01 {
    public static void main(String[] args) {
        int m = 4;
        int n = 5;
        String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        int answer = solution(m, n, board);
        System.out.println(answer);
    }

    public static String[][] boards;
    public static boolean[][] checked;
    public static final String EMPTY = "-1";
    public static int[] xArray = {0, 0, 1, 1};
    public static int[] yArray = {0, 1, 0, 1};

    public static int solution(int m, int n, String[] board) {
        int answer = 0;
        boards = new String[m][n];
        checked = new boolean[m][n];

        makeBoard(board); // 2차원 배열 블록 생성

        boolean flag = true;
        while (flag) {

            // 4블록 영역 체크 여부 초기화
            for (int i = 0; i < checked.length; i++) {
                Arrays.fill(checked[i], false);
            }

            flag = findFourSections(); // 4블록 영역들을 찾고 지워줌
            dropBlocks(); // 윗 블록들을 떨어뜨려 빈 영역을 채움
        }

        for (int i = 0; i < boards.length; i++) {
            for (int j = 0; j < boards[i].length; j++) {
                if (boards[i][j].equals(EMPTY)) {
                    answer++;
                }
            }
        }
        return answer;
    }

    public static void makeBoard(String[] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                boards[i][j] = board[i].charAt(j) + "";
            }
        }
    }

    public static boolean findFourSections() {
        boolean flag = false;
        for (int i = 0; i < boards.length - 1; i++) {
            for (int j = 0; j < boards[i].length - 1; j++) {
                if (!boards[i][j].equals(EMPTY)) { // 빈영역(앞서 4블록으로 체크되어 지워진 영역임)
                    if (isFourBlockSameCharacter(boards[i][j], i, j)) {
                        flag = true;
                        setChecked(i, j);
                    }
                }
            }
        }
        return flag;
    }

    public static boolean isFourBlockSameCharacter(String s, int x, int y) {
        for (int i = 0; i < 4; i++) {
            if (!boards[x + xArray[i]][y + yArray[i]].equals(s)) {
                return false;
            }
        }
        return true;
    }

    public static void setChecked(int x, int y) {
        for (int i = 0; i < 4; i++) {
            checked[x + xArray[i]][y + yArray[i]] = true;
        }
    }

    public static void dropBlocks() {
        for (int i = boards.length - 1; i >= 0; i--) {
            for (int j = 0; j < boards[i].length; j++) {
                if (checked[i][j]) {
                    boards[i][j] = EMPTY;
                    int index = i;
                    while (index >= 0 && checked[index][j]) {
                        index--;
                    }

                    if (index >= 0) {
                        boards[i][j] = boards[index][j];
                        checked[index][j] = true;
                    }
                }
            }
        }
    }
}
