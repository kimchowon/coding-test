package programmers.level2;

public class Test24 {
    public static void main(String[] args) {

        int[][] land = {{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}};

        System.out.println("answer :" + solution(land));
    }

    public static int solution(int[][] land) {

        int[][] tempArray = new int[land.length][land[0].length];

        for (int i = land.length - 1; i > 0; i--) {
            for (int j = 3; j >= 0; j--) {

                int num = tempArray[i][j] == 0 ? land[i][j] : tempArray[i][j];

                for (int k = 0; k < 4; k++) {
                    if (j != k) {
                        int sum = num + land[i - 1][k];
                        tempArray[i - 1][k] = Math.max(tempArray[i - 1][k], sum);
                    }
                }
            }
        }

        int answer = tempArray[0][0];
        for (int i = 1; i < 4; i++) {
            answer = Math.max(answer, tempArray[0][i]);
        }

        return answer;
    }

}
