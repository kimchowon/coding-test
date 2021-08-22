package programmers.level3;

public class Test05 {
    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        int[][] puddles = {{2, 2}, {3, 3}};

        System.out.println("answer = " + solution(m, n, puddles));
    }

    public static int solution(int m, int n, int[][] puddles) {
        int answer;
        int NUM = 1000000007;

        int[][] locations = new int[n + 1][m + 1];

        int width_length = locations.length;
        int height_length = locations[0].length;
        for (int i = width_length - 1; i >= 0; i--) {
            for (int j = height_length - 1; j >= 0; j--) {

                // 물웅덩이면 0으로 초기화
                if (isPuddles(i, j, puddles)) {
                    //  System.out.println("(" + i + "," + j + ")" + "is puddles");
                    locations[i][j] = 0;
                    continue;
                }

                // 오른쪽, 아래 모두 범위를 벗어나는 좌표이면 1로 초기화
                if ((i + 1) >= width_length && (j + 1) >= height_length) {
                    locations[i][j] = 1;
                } else {
                    // 오른쪽 좌표가 범위내에 있다면 더함
                    if ((i + 1) < width_length) {
                        locations[i][j] += (locations[i + 1][j] % NUM);
                    }

                    // 아래쪽 좌표가 범위내에 있다면 더함
                    if ((j + 1) < height_length) {
                        locations[i][j] += (locations[i][j + 1] % NUM);
                    }
                }

                // 밀러의 연산
                locations[i][j] = locations[i][j] % NUM;
                // System.out.print("(" + i + "," + j + ")" + "[" + locations[i][j] + "]  ");
            }
            // System.out.println();
        }

        answer = locations[1][1];
        return answer;
    }

    /**
     * 물 웅덩이인지 체크
     * @param x
     * @param y
     * @param puddles
     * @return
     */
    public static boolean isPuddles(int x, int y, int[][] puddles) {
        for (int i = 0; i < puddles.length; i++) {
            int[] array = puddles[i];
            if (array[0] == y && array[1] == x) {
                return true;
            }
        }
        return false;
    }
}
