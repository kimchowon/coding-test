package graph;

public class FloydWarshall {

    public static void main(String[] args) {
        floydWarshall();
    }

    static int number = 4;
    static int infinite = 100000;

    static int array[][] = {{0, 5, infinite, 8},
            {7, 0, 9, infinite},
            {2, infinite, 0, 4},
            {infinite, infinite, 3, 0}};

    static public void floydWarshall() {
        // 결과 그래프
        int[][] resultArray = new int[number][number];

        // 결과 그래프 초기 세팅
        // 원래 그래프와 동일하게
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                resultArray[i][j] = array[i][j];
            }
        }

        // k = 거쳐가는 노드
        for (int k = 0; k < number; k++) {
            // i = 출발 노드
            for (int i = 0; i < number; i++) {
                // j = 도착 노드
                for (int j = 0; j < number; j++) {
                    if (resultArray[i][k] + resultArray[k][j] < resultArray[i][j]) {
                        resultArray[i][j] = resultArray[i][k] + resultArray[k][j];
                    }
                }
            }
        }

        // 결과를 출력
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                System.out.print(resultArray[i][j] + " ");
            }
            System.out.println();
        }

    }
}
