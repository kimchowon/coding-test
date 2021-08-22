package programmers.level3;

public class Test17_2 {
    public static void main(String[] args) {

        int n = 4;
        int results[][] =// {{4,2}, {4,3},{3,2}, {1,2}, {2,5}};
                {{1,2},{2,3},{3,4}};
        solution(n, results);
    }

    public static int solution(int n, int[][] results) {
        int answer = 0;

        int answerArray[][] = new int[n+1][n+1];

        for (int i=0; i < results.length; i++) {
            int x = results[i][0];
            int y = results[i][1];
            answerArray[x][y] = 1;
        }

        // k = 거쳐가는 노드
        for (int k=0; k < n+1; k++) {
            // i = 출발 노드
            for (int i=0; i < n+1; i++) {
                // j = 도착 노드
                for (int j=0; j < n+1; j++) {
                    if (answerArray[i][k]==1 && answerArray[k][j]==1) {
                        answerArray[i][j] = 1;
                    }
                }
            }

        }

        for (int i=1; i < answerArray.length; i++) {
            boolean check = true;
            for (int j=1; j < answerArray[i].length; j++) {
                if (i!=j && answerArray[i][j]==0 && answerArray[j][i]==0) {
                    check = false;
                }
            }

            if (check) {
                answer++;
            }
        }
        return answer;
    }
}
