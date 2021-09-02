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

    static class Card {
        int value;
        int x;
        int y;
        int count;

        public Card(int value, int x, int y, int count) {
            this.value = value;
            this.x = x;
            this.y = y;
            this.count = count;
        }

        public Card(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static List<Card> cardList;
    public static int[][] BOARD = new int[4][4];
    public static int ANSWER = Integer.MAX_VALUE;
    public static int R;
    public static int C;

    public static int solution(int[][] board, int r, int c) {
        int answer = 0;

        init(board);
        R = r;
        C = c;
        cardList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] > 0) {
                    Card card = new Card(board[i][j], i, j, 0);
                    cardList.add(card);
                }
            }
        }

        // 카드 값 순차로 정렬
        cardList.sort(Comparator.comparingInt(o -> o.value));

        Card[] item = new Card[cardList.size()];
        for (int i = 0; i < cardList.size(); i += 2) {
            Card card1 = cardList.get(i);
            item[i] = new Card(card1.value, card1.x, card1.y, 0);

            Card card2 = cardList.get(i + 1);
            item[i + 1] = new Card(-card2.value, card2.x, card2.y, 0);
        }

        Card[] bucket = new Card[item.length];
        permutation(item, bucket, bucket.length, board);

        return ANSWER + cardList.size();
    }


    public static int order = 0;

    public static void permutation(Card[] items, Card[] bucket, int k, int[][] board) {
        if (k == 0) {
            int total = 0;
            Card initCursor = new Card(R, C, 0);
            Card start = bucket[0];
            total += getNearestPath(initCursor, start);
            BOARD[start.x][start.y] = 0;

            for (int i = 0; i < bucket.length - 1; i++) {
                start = bucket[i];
                Card end = bucket[i + 1];
                int nearestPath = getNearestPath(start, end);
                BOARD[start.x][start.y] = 0;
                BOARD[end.x][end.y] = 0;
                total += nearestPath;
            }
            ANSWER = Math.min(ANSWER, total);
            init(board);
            return;
        }

        int lastIndex = bucket.length - k - 1;
        for (int i = 0; i < items.length; i++) {
            if (bucket.length == k) {
                bucket[0] = items[i];
                permutation(items, bucket, k - 1, board);
            } else {
                // 이번에 넣을 인덱스가 짝수번째이면
                if ((lastIndex + 1) % 2 == 0) {
                    boolean isSame = false;
                    for (int j = 0; j <= lastIndex; j++) {
                        if (bucket[j].value == items[i].value) {
                            isSame = true;
                            break;
                        }
                    }

                    if (!isSame) {
                        bucket[lastIndex + 1] = items[i];
                        permutation(items, bucket, k - 1, board);
                    }
                } else { // 이번에 넣을 인덱스가 홀수번째이면
                    if (bucket[lastIndex].value == -items[i].value) {
                        bucket[lastIndex + 1] = items[i];
                        permutation(items, bucket, k - 1, board);
                    }
                }
            }
        }
    }

    public static int[] moveX = {-1, +1, 0, 0};
    public static int[] moveY = {0, 0, -1, +1};
    public static int getNearestPath(Card start, Card end) {
        boolean[][] visited = new boolean[4][4];
        Queue<Card> queue = new LinkedList<>();
        queue.add(start);
        int count = 0;

        while (!queue.isEmpty()) {
            Card cur = queue.poll();

            if (BOARD[cur.x][cur.y] > 0) {
                visited[cur.x][cur.y] = true;
            }

            if (cur.x == end.x && cur.y == end.y) {
                count = cur.count;
                break;
            }

            // 상하좌우 한칸씩 이동
            for (int i = 0; i < 4; i++) {
                int x = cur.x + moveX[i];
                int y = cur.y + moveY[i];

                if (validationCheckCoordinate(x, y)) {
                    if (!visited[x][y]) {
                        queue.add(new Card(x, y, cur.count + 1));
                    }
                }
            }

            // ctrl + 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                Card nearestCard = getNearestCard(cur, moveX[i], moveY[i]);
                int x = nearestCard.x;
                int y = nearestCard.y;
                if (validationCheckCoordinate(x, y)) {
                    if (!visited[x][y]) {
                        queue.add(new Card(x, y, cur.count + 1));
                    }
                }
            }
        }
        return count;
    }

    public static Card getNearestCard(Card cur, int moveX, int moveY) {
        int x = cur.x;
        int y = cur.y;
        Card nearestCard = new Card(x, y, 0);

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

    public static void init(int[][] board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                BOARD[i][j] = board[i][j];
            }
        }
    }
}