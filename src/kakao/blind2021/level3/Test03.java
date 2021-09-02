package kakao.blind2021.level3;

import java.util.*;

/**
 * 카드 짝 맞추기
 */
public class Test03 {
    public static void main(String[] args) {
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

        public Card() {

        }

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

        public String toString() {
            return "[" + x + ", " + y + "]";
        }
    }

    public static List<Card> cardList;
    public static int[][] BOARD;
    public static int ANSWER = Integer.MAX_VALUE;
    public static int R;
    public static int C;

    public static int solution(int[][] board, int r, int c) {
        int answer = 0;

        BOARD = board;
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
        permutation(item, bucket, bucket.length);

        return ANSWER;
    }

    public static void permutation(Card[] items, Card[] bucket, int k) {
        if (k == 0) {
            int total = 0;
            Card initCursor = new Card(R, C, 0);

            // 커서부터 첫번째 위치까지 가는 최단 경로
            total += getNearestPath(initCursor, bucket[0]);
            System.out.println(initCursor + " ~ " + bucket[0].toString() + " = " + total);

            for (int i = 0; i < bucket.length - 1; i++) {
                int nearestPath = getNearestPath(bucket[i], bucket[i + 1]);
                System.out.println(bucket[i].toString() + " ~ " + bucket[i + 1].toString() + " = " + nearestPath);

                total += nearestPath;

            }
            System.out.println("total = " + total);
            System.out.println();
            System.out.println();
            return;
        }

        int lastIndex = bucket.length - k - 1;
        for (int i = 0; i < items.length; i++) {
            if (bucket.length == k) {
                bucket[0] = items[i];
                permutation(items, bucket, k - 1);
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
                        permutation(items, bucket, k - 1);
                    }
                } else { // 이번에 넣을 인덱스가 홀수번째이면
                    if (bucket[lastIndex].value == -items[i].value) {
                        bucket[lastIndex + 1] = items[i];
                        permutation(items, bucket, k - 1);
                    }
                }
            }
        }
    }

    public static int[] moveX = {0, 0, -1, +1};
    public static int[] moveY = {+1, -1, 0, 0};

    public static int getNearestPath(Card start, Card end) {
        boolean[][] visited = new boolean[4][4];
        Queue<Card> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            Card cur = queue.poll();

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
                        visited[x][y] = true;
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
                        visited[x][y] = true;
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

            if (!validationCheckCoordinate(x,y)) {
                break;
            }

            if (BOARD[x][y] > 0) {
                nearestCard.x = x;
                nearestCard.y = y;
                break;
            }
        }

        return nearestCard;
    }

    public static boolean validationCheckEndBoard(int x, int y) {
        if (x == 0 || x == 3 || y == 0 || y == 3) {
            return true;
        }
        return false;
    }

    public static boolean validationCheckCoordinate(int x, int y) {
        if (x < 0 || x >= 4 || y < 0 || y >= 4) {
            return false;
        }
        return true;
    }
}
