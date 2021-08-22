package programmers.level3;

public class Test17 {
    public static void main(String[] args) {
        int n = 5;
        int[][] results =// {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
                //{{5, 4}, {5, 2}, {5, 1}, {5, 3}, {4, 3}, {1, 3}, {2, 3}, {4, 1}, {4, 2}};
                //{{3, 5}, {4, 2}, {4, 5}, {5, 1}, {5, 2}};
                //{{1,2}, {1,3},{1,4}, {2,3}};
                //{{1, 2}, {2, 3}, {3, 4}, {5, 6}, {6, 7}, {7, 8}};
                {{1,2}, {2,3}, {3,4}, {4,5}};

        System.out.println("answer=" + solution(n, results));
    }

    public static class Rank {
        int rank;
        int winCnt;
        int loseCnt;

        Rank(int rank, int winCnt, int loseCnt) {
            this.rank = rank;
            this.winCnt = winCnt;
            this.loseCnt = loseCnt;
        }
    }

    public static int solution(int n, int[][] results) {
        int answer = 0;
        int[][] rankingArray = new int[n + 1][n + 1];

        Rank[] ranks = new Rank[n + 1];
        for (int i = 0; i < ranks.length; i++) {
            ranks[i] = new Rank(0,0, 0);
        }

        boolean check = false;
        while (true) {
            check = false;
            for (int i = 0; i < results.length; i++) {
                int winner = results[i][0];
                int loser = results[i][1];

                if (rankingArray[winner][loser] == 0) {
                    rankingArray[winner][loser] = 1;

                    ranks[winner].winCnt++;
                    ranks[loser].loseCnt++;
                    check = true;
                }else {
                    for (int j = 0; j < rankingArray[loser].length; j++) {
                        if (rankingArray[loser][j] == 1 && rankingArray[winner][j] == 0) {
                            rankingArray[winner][j] = 1;

                            ranks[winner].winCnt++;
                            ranks[j].loseCnt++;
                            check = true;
                        }
                    }
                }
            }

            for (int i=0; i < ranks.length; i++) {
                Rank rank = ranks[i];
                if (rank.winCnt + rank.loseCnt + 1 == n && rank.rank==0) {
                    rank.rank = n - rank.winCnt;
                    answer++;
                }
            }

            if (!check) {
                break;
            }
        }

        return answer;
    }
}
