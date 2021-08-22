package greedy;

/**
 * 조이 스틱
 */
public class Solution3 {

    public static void main(String[] args) {
        System.out.println("answer : " + solution("JEROEN"));
    }

    public static int solution(String name) {
        int answer = 0;

        boolean checked[] = new boolean[name.length()];

        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == 'A') {
                checked[i] = true;
            }
        }

        // 현재의 커서를 정함
        int cursor = 0;
        int tempCursor = 0;
        int A = 'A';
        int Z = 'Z';
        for (int i = 0; i < name.length(); i++) {

            boolean isAllTrue = isAllTrue(checked);

            checked[cursor] = true;
            if (!isAllTrue) {
                int n = name.charAt(cursor);

                if ((n - A) >= 14) {
                    answer += (Z - n) + 1;
                } else {
                    answer += (n - A);
                }

                // 다음 커서를 선택
                int min = 100;
                int realMin = 0;
                for (int j = 0; j < name.length(); j++) {
                    int m = name.charAt(j);
                    boolean isA = (A==m) ? true : false;
                    if (isA != true && !checked[j]) {
                        int left = Math.abs(name.length() - j + cursor);
                        int right = Math.abs(j - cursor);

                        if (Math.min(left, right) < min) {
                            min = Math.min(left, right);
                            realMin = min;
                            tempCursor = j;
                        }
                    }
                }
                answer += realMin;
                cursor = tempCursor;
            }
        }
        return answer;
    }

    public static boolean isAllTrue(boolean[] checked) {
        for (int i = 0; i < checked.length; i++) {
            if (checked[i] == false) {
                return false;
            }
        }
        return true;
    }
}
