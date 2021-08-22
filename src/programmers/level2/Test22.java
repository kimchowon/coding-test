package programmers.level2;

import java.util.Arrays;
import java.util.Stack;

public class Test22 {
    public static long answer = 0;
    public static String postfix = "";

    public static void main(String[] args) {
        String expression = "50*6-3*2";
        System.out.println("answer : " + solution(expression));
    }

    public static long solution(String expression) {
        answer = 0;
        postfix = expression;

        int[] items = {0, 1, 2};
        int[] buckets = new int[3];

        // 우선순위 경우의 수를 모두 구함 - 순열
        permutation(items, buckets, buckets.length);

        return answer;
    }

    /**
     * 순열 - 연산자 우선순위 경우의 수를 모두 구함
     * @param items
     * @param buckets
     * @param k
     */
    public static void permutation(int[] items, int[] buckets, int k) {
        if (k == 0) {

            // 연산자 우선순위와 중위표기법을 이용하여 후위표기법 생성
            String[] infix = postfixToInfix(postfix, buckets);

            // 후위표기법 계산 결과
            long result = getInfixResult(infix);

            // 가장 큰 값을 answer에 대입
            answer = Math.max(answer, result);
            return;
        }

        int lastIndex = buckets.length - k - 1;
        for (int i = 0; i < items.length; i++) {
            boolean flag = true;

            for (int j = 0; j <= lastIndex; j++) {
                if (buckets[j] == items[i]) {
                    flag = false;
                }
            }

            if (flag) {
                buckets[lastIndex + 1] = items[i];
                permutation(items, buckets, k - 1);
            }
        }
    }

    /**
     * 중위 표기법 -> 후위 표기법으로 변환
     * @param postfix
     * @param priorities
     * @return
     */
    public static String[] postfixToInfix(String postfix, int[] priorities) {
        // String을 배열로 변환
        String[] postfixArray = strToArray(postfix);
        String[] infixArray = new String[postfixArray.length];
        String[] op = {"+", "-", "*"};
        Stack<String> stack = new Stack<>();
        int index = 0;

        for (int i = 0; i < postfixArray.length; i++) {
            // 배열의 원소가 연산자이면
            if (Arrays.asList(op).contains(postfixArray[i])) {
                // 스택이 비어있으면
                if (stack.isEmpty()) {
                    // 연산자를 push
                    stack.push(postfixArray[i]);
                } else {
                    // 스택이 비어있지 않다면
                    // 스택에 먼저 들어있는 연산자들이 현재 연산자보다 우선순위가 높다면 pop
                    while (!stack.isEmpty() && compareToOperation(postfixArray[i], stack.peek(), priorities)) {
                        // 후위표기법 계산식에 스택에서 pop한 연산자 출력
                        infixArray[index++] = stack.pop();
                    }
                    // 현재 연산자를 스택에 push
                    stack.push(postfixArray[i]);
                }
            // 피연산자이면 후위표기법 계산식에 바로 출력
            } else {
                infixArray[index++] = postfixArray[i];
            }
        }

        // 스택에 남아있던 연산자들 모두 후위표기법 계산식에 pop하여 출력
        while (!stack.isEmpty()) {
            infixArray[index++] = stack.pop();
        }

        return infixArray;
    }

    public static String[] strToArray(String postfix) {
        String[] numStrArray = postfix.replaceAll("[^0-9]", " ").split(" ");
        String[] opStrArray = postfix.replaceAll("[^[+]-[*]]", "").split("");

        String[] result = new String[numStrArray.length + opStrArray.length];
        int index = 0;
        for (int i = 0; i < numStrArray.length; i++) {
            result[index++] = numStrArray[i];

            if (i != numStrArray.length - 1) {
                result[index++] = opStrArray[i];
            }
        }
        return result;
    }

    /**
     * 연산자 우선순위 체크 로직
     * @param op1
     * @param op2
     * @param priorities
     * @return
     */
    public static boolean compareToOperation(String op1, String op2, int[] priorities) {
        String[] op = {"+", "-", "*"};

        if (op1.equals(op[priorities[0]])) {
            if (op2.equals(op[priorities[0]])) {
                return true;
            }
        }

        if (op1.equals(op[priorities[1]])) {
            if (op2.equals(op[priorities[0]]) || op2.equals(op[priorities[1]])) {
                return true;
            }
        }

        if (op1.equals(op[priorities[2]])) {
            if (op2.equals(op[priorities[0]]) || op2.equals(op[priorities[1]]) || op2.equals(op[priorities[2]])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 후위 표기법 계산 메서드
     * @param infix
     * @return
     */
    public static long getInfixResult(String[] infix) {
        long answer = 0;
        String[] op = {"+", "-", "*"};
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < infix.length; i++) {
            // 후위표기법의 현재 원소가 '연산자'인 경우
            if (Arrays.asList(op).contains(infix[i])) {
                String operation = infix[i];

                // 스택에서 피연산자들 2개를 pop
                long second = Long.parseLong(stack.pop());
                long first = Long.parseLong(stack.pop());

                // 현재 원소의 종류에 따라 피연산자들을 계산한 결과값을 스택에 push
                switch (operation) {
                    case "+":
                        stack.push(String.valueOf(first + second));
                        break;
                    case "-":
                        stack.push(String.valueOf(first - second));
                        break;
                    case "*":
                        stack.push(String.valueOf(first * second));
                        break;
                    case "/":
                        stack.push(String.valueOf(first / second));
                        break;
                }
            // 현재 원소가 피연산자이면 스택에 push
            } else {
                stack.push(infix[i]);
            }
        }

        answer = Math.abs(Long.parseLong(stack.pop()));
        return answer;
    }
}
