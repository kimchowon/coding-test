package codility;

import java.util.Stack;

/**
 * Lesson1.Iterations - BinaryGap
 */
public class Test01 {
    public static void main(String[] args) {
        int N = 32; // 10000010001
        int answer = solution(N);
        System.out.println(answer);
    }

    public static int solution(int N) {
        String binary = getConventN(N, 2);
        int answer = getMaxGaps(binary);
        return answer;
    }

    // 10진수를 N진수로 변환
    public static String getConventN(int num, int n) {
        String result = "";
        if (num == 0) {
            return "0";
        }

        while (num > 0) {
            int share = num / n;
            int remainder = num % n;
            if (remainder > 9) {
                result = (char) (remainder + 55) + result;
            } else {
                result = remainder + result;
            }
            num = share;
        }
        return result;
    }

    public static int getMaxGaps(String binary) {
        Stack<Integer> stack = new Stack<>();
        String[] binaryList = binary.split("");

        int max = Integer.MIN_VALUE;
        for (String b : binaryList) {
            int num = Integer.parseInt(b);
            if (stack.isEmpty() && num == 1) {
                stack.push(num);
            } else if (num == 0) {
                stack.push(num);
            } else if (num == 1) {
                int count = 0;
                while (!stack.isEmpty()) {
                    if (stack.pop() == 0) {
                        count++;
                    }
                }
                stack.push(num);
                max = Math.max(max, count);
            }
        }
        return max > Integer.MIN_VALUE ? max : 0;
    }
}
