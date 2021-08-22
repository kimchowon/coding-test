package programmers.level2;

import java.util.*;
public class Test15 {
    public static void main(String[] args) {

        String s = //"()()";
              //  "(())()";
               // ")()(";
                //"(()(";
                "()())";

        System.out.println(solution(s));
    }

    public static boolean solution(String s) {
        Queue<Character> queue = new LinkedList<>();

        // 처음부터 '(' 가 아닌 ')' 가 먼저 등장한다면 애초에 올바른 괄호가 아니므로 false 반환하고 종료
        if (s.charAt(0)==')') {
            return false;
        }

        for (int i=0; i < s.length(); i++) {
            char c = s.charAt(i);

            // '(' 면 큐에 add
            if (c == '(') {
                queue.add(c);
                continue;
            }

            // 큐가 비어있고( 앞에 있던 '(' 들이 모두 짝을 만나 처리됨)) ')'가 등장한 경우,
            // 괄호의 짝이 맞지 않으므로 false 반환하고 종료
            if (queue.isEmpty() && c==')') {
                return false;
            }

            // ')' 인 경우 큐에서 '(' 하나를 poll (짝을 맞춰 처리)
            if (!queue.isEmpty()) {
                queue.poll();
            }
        }

        // 큐가 모두 비었으면 (괄호들이 짝이 모두 맞으면) true반환하고 종료
        if (queue.isEmpty()) {
            return true;
        }

        return false;
    }
}
