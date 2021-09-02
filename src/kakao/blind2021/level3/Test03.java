package kakao.blind2021.level3;

import java.util.*;

/**
 * 카드 짝 맞추기
 */
public class Test03 {
    public static void main(String[] args) {
/*        int[][] board = {{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}};
        int r = 1;
        int c = 0;*/

        int[][] board = {{3, 0, 0, 2}, {0, 0, 1, 0}, {0, 1, 0, 0}, {2, 0, 0, 3}};
        int r = 0;
        int c = 1;

        int answer = solution(board, r, c);
        System.out.println(answer);
    }

    static class Point {
        int x;
        int y;
        int count;
        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static int[][] BOARD = new int[4][4];
    public static int ANSWER = Integer.MAX_VALUE;
    public static int R;
    public static int C;

    public static int solution(int[][] board, int r, int c) {
        initBoard(board);
        R = r;
        C = c;
        List<Point> cardList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] > 0) {
                    Point card = new Point(i, j, 0);
                    cardList.add(card);
                }
            }
        }

        Point[] bucket = new Point[cardList.size()];
        permutation(cardList, bucket, bucket.length, board);
        return ANSWER + cardList.size();
    }

    public static void initBoard(int[][] board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                BOARD[i][j] = board[i][j];
            }
        }
    }

    public static void permutation(List<Point> pointList, Point[] bucket, int k, int[][] board) {
        if (k == 0) {
            int total = 0;
            Point start = new Point(R, C, 0);
            Point end = bucket[0];
            total += getNearestPath(start, end);
            BOARD[start.x][start.y] = 0;

            for (int i = 0; i < bucket.length - 1; i++) {
                start = bucket[i];
                end = bucket[i + 1];
                total += getNearestPath(start, end);
                BOARD[start.x][start.y] = 0;
                BOARD[end.x][end.y] = 0;
            }
            ANSWER = Math.min(ANSWER, total);
            initBoard(board);
            return;
        }

        int lastIndex = bucket.length - k - 1;
        for (int i = 0; i < pointList.size(); i++) {
            if (bucket.length == k) {
                bucket[0] = pointList.get(i);
                permutation(pointList, bucket, k - 1, board);
            } else {
                // 이번에 넣을 인덱스가 짝수번째이면
                if ((lastIndex + 1) % 2 == 0) {
                    boolean isSame = false;
                    for (int j = 0; j <= lastIndex; j++) {
                        if (bucket[j].x == pointList.get(i).x && bucket[j].y == pointList.get(i).y) {
                            isSame = true;
                            break;
                        }
                    }

                    if (!isSame) {
                        bucket[lastIndex + 1] = pointList.get(i);
                        permutation(pointList, bucket, k - 1, board);
                    }
                } else { // 이번에 넣을 인덱스가 홀수번째이면
                    Point last = bucket[lastIndex];
                    Point cur = pointList.get(i);
                    if (BOARD[last.x][last.y] == BOARD[cur.x][cur.y]) {
                        if (bucket[lastIndex].x != pointList.get(i).x || bucket[lastIndex].y != pointList.get(i).y) {
                            bucket[lastIndex + 1] = pointList.get(i);
                            permutation(pointList, bucket, k - 1, board);
                        }
                    }
                }
            }
        }
    }

    public static int[] moveX = {-1, +1, 0, 0};
    public static int[] moveY = {0, 0, -1, +1};
    public static int getNearestPath(Point start, Point end) {
        boolean[][] visited = new boolean[4][4];
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        int count = 0;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            visited[cur.x][cur.y] = true;

            if (cur.x == end.x && cur.y == end.y) {
                return cur.count;
            }

            for (int i = 0; i < 4; i++) {
                // 상하좌우 한칸씩 이동
                int x = cur.x + moveX[i];
                int y = cur.y + moveY[i];
                if (validationCheckCoordinate(x, y) && !visited[x][y]) {
                    queue.add(new Point(x, y, cur.count + 1));
                }

                // ctrl + 상하좌우 이동
                Point nearestCard = getNearestCard(cur, moveX[i], moveY[i]);
                x = nearestCard.x;
                y = nearestCard.y;
                if (validationCheckCoordinate(x, y) && !visited[x][y]) {
                    queue.add(new Point(x, y, cur.count + 1));
                }
            }
        }
        return count;
    }

    public static Point getNearestCard(Point cur, int moveX, int moveY) {
        int x = cur.x;
        int y = cur.y;
        Point nearestCard = new Point(x, y, 0);

        while (true) {
            x += moveX;
            y += moveY;

            if (!validationCheckCoordinate(x, y)) {
                break;
            } else {
                nearestCard.x = x;
                nearestCard.y = y;
            }

            if (BOARD[x][y] > 0) {
                return nearestCard;
            }
        }
        return nearestCard;
    }

    public static boolean validationCheckCoordinate(int x, int y) {
        if (x < 0 || x >= 4 || y < 0 || y >= 4) {
            return false;
        }
        return true;
    }
}