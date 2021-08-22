package greedy;

import java.util.*;

/**
 * 큰 수 만들기
 */
public class Solution2 {

    public static void main(String[] args) {
        String number = "4177252841";
        System.out.println("answer = " + solution(number, 4));

    }

    public static String solution(String number, int k) {
        char[] result = new char[number.length() - k];
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<number.length(); i++) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {

                System.out.println("k : " + k);
                stack.pop();
            }
            stack.push(c);
        }
        for (int i=0; i<result.length; i++) {
            result[i] = stack.get(i);
        }
        return new String(result);
    }

}
