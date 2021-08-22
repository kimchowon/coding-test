package programmers.level2;

public class Test14 {

    public static void main(String[] args) {

        int[][] board = /*{{0,0,0,0,0,0},
                {0,1,1,1,1,0},
                {0,0,1,1,1,1},
                {1,0,1,1,1,0},
                {1,0,1,0,1,1},
                {1,0,0,1,0,0}};*/
                {{1, 0}, {0, 0}};

        System.out.println(solution(board));
    }

    public static int solution(int[][] board) {
        int answer = 0;

        // (1,1)행 부터 순회 시작
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    int min = Math.min(board[i - 1][j], Math.min(board[i][j - 1], board[i - 1][j - 1]));
                    board[i][j] = min + 1;

                    answer = Math.max(answer, board[i][j]);
                }
            }
        }

        // 위 순회 결과 모든 칸이 0인 경우
        if (answer == 0) {

            // 첫번째 행들을 순회 (0, i)
            for (int i = 0; i < board[0].length; i++) {
                // 1이 존재하면 그대로 return
                if (board[0][i] == 1) {
                    return 1;
                }
            }

            // 첫번째 열들을 순회 (0, i)
            for (int i = 0; i < board.length; i++) {
                // 1이 존재하면 그대로 return
                if (board[i][0] == 1) {
                    return 1;
                }
            }
            return 0;
        }

        return (int) Math.pow(answer, 2);
    }
}
