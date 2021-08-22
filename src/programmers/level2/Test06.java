package programmers.level2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Test06 {
    public static void main(String[] args) {
        String s = "aabbaccc";
        System.out.println("answer : " + solution(s));

    }

    public static int solution(String s) {
        int answer = s.length();

        List<String> strList;
        for (int range = 1; range < s.length(); range++) {
            // 단위 개수만큼 문자열을 잘라 리스트 생성
            strList = makeStringListAboutRange(s, range);

            String compress = makeCompressString(strList);
            answer = Math.min(answer, compress.length());
        }
        return answer;
    }

    public static List<String> makeStringListAboutRange(String s, int range) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < s.length(); i += range) {
            if (i + range > s.length()) {
                stringList.add(s.substring(i));
            } else {
                stringList.add(s.substring(i, i + range));
            }
        }
        return stringList;
    }

    public static String makeCompressString(List<String> strList) {
        String compress = "";
        Stack<String> stack = new Stack<>();
        int count = 0;
        stack.push(strList.get(0));

        int size = strList.size();
        for (int i = 1; i <= size; i++) {
            if (!stack.isEmpty()) {

                // stack에서 peek한 문자
                String curString = stack.peek();

                // 스택에서 peek한 문자와 현재 str list 인덱스 문자와 같다면 stack에 push
                if (i < size && curString.equals(strList.get(i))) {
                    stack.push(strList.get(i));
                    continue;
                }

                // 다르다면
                while (!stack.isEmpty()) {
                    stack.pop();
                    count++;
                }

                if (i < size) {
                    // stack에 저장되어있던 문자들을 모두 꺼내고 현재 인덱스 문자를 stack에 push
                    stack.push(strList.get(i));
                }

                compress += (count > 1 ? count : "") + curString;
                count = 0;
            }
        }
        return compress;
    }
}
