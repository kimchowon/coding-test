package programmers.level2;

public class Test04 {

    public static void main(String[] args) {

        //  String p = "(()())()()";
        //  String p = "()))((()";
        String p = "()))((()";
        System.out.println("answer : " + solution(p));
    }

    public static String solution(String p) {
        String answer = "";
        if (p.isEmpty()) {
            return "";
        }

        String[] divides = divideTwoWords(p);
        if (isCorrectWords(divides[0])) {
            return divides[0] + solution(divides[1]);
        } else {
            answer += "(" + solution(divides[1]) + ")";

            for (int i = 1; i < divides[0].length() - 1; i++) {
                if (divides[0].charAt(i) == '(') {
                    answer += ")";
                } else {
                    answer += "(";
                }
            }
        }
        return answer;
    }

    public static boolean isCorrectWords(String p) {
        int front = 0;
        for(int i=0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                front++;
                continue;
            }
            if (p.charAt(i) == ')' && front == 0) {
                return false;
            }
            front--;
        }
        return true;
    }

    public static String[] divideTwoWords(String p) {
        String[] divides = new String[2];
        for (int i = 0; i < divides.length; i++) {
            divides[i] = "";
        }

        int front = 0;
        int end = 0;
        for(int i=0; i < p.length(); i++) {
            if (front != 0 && end != 0 && front == end) {
                divides[1] += p.charAt(i);
                continue;
            }
            if (p.charAt(i) == '(') {
                front++;
            }
            if (p.charAt(i) == ')') {
                end++;
            }
            divides[0] += p.charAt(i);
        }
        return divides;
    }
}
