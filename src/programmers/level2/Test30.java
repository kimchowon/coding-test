package programmers.level2;

public class Test30 {
    public static boolean isBlock = true;

    public static void main(String[] args) {
        int m = 6;
        int n = 6;
        String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        // {"CCBDE", "AAADE", "AAABF", "CCBBF"};

        System.out.println("answer : " + solution(m, n, board));
    }

    public static int solution(int m, int n, String[] board) {
        int answer = 0;
        isBlock = true;

        String[][] arr = new String[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = board[i].charAt(j) + "";
            }
        }

        while (isBlock) {
            isBlock = false;

            boolean[][] visited = new boolean[m][n];
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (!arr[i][j].equals("0")) {
                        dfs(i, j, arr, visited);
                    }
                }
            }
            reSort(arr, visited);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j].equals("0")) {
                    answer++;
                }
            }
        }
        return answer;
    }

    public static void dfs(int x, int y, String[][] arr, boolean[][] visited) {
        String curW = arr[x][y];

        int curX = x;
        int curY = y;

        // 오
        int rightX = curX;
        int rightY = curY + 1;
        if (!curW.equals(arr[rightX][rightY])) {
            return;
        }

        // 하
        int bottomX = curX + 1;
        int bottomY = curY;
        if (!curW.equals(arr[bottomX][bottomY])) {
            return;
        }

        // 대각선
        int diagonalX = curX + 1;
        int diagonalY = curY + 1;
        if (!curW.equals(arr[diagonalX][diagonalY])) {
            return;
        }

        visited[curX][curY] = true;
        visited[rightX][rightY] = true;
        visited[bottomX][bottomY] = true;
        visited[diagonalX][diagonalY] = true;

        isBlock = true;
    }

    public static void reSort(String[][] arr, boolean[][] visited) {
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = arr.length - 1; j >= 0; j--) {

                boolean flag = false;

                if (arr[j][i].equals("0") || visited[j][i]) {
                    for (int k = j - 1; k >= 0; k--) {

                        if (!arr[k][i].equals("0") && !visited[k][i]) {
                            arr[j][i] = arr[k][i];
                            arr[k][i] = "0";
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        arr[j][i] = "0";
                    }
                }
            }
        }
    }
}
