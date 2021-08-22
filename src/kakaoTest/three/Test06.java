package kakaoTest.three;

import java.util.Arrays;

public class Test06 {
    public static void main(String args[]) {
        String[] files = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        String[] answerList = solution(files);
        System.out.println(Arrays.toString(answerList));
    }

    public static String[] solution(String[] files) {
        Arrays.sort(files, (o1, o2) -> {
            String HEAD1 = o1.toLowerCase().split("[0-9]")[0];
            String HEAD2 = o2.toLowerCase().split("[0-9]")[0];

            int result = HEAD1.compareTo(HEAD2);
            if (result == 0) {
                int NUMBER1 = getNumber(o1.substring(HEAD1.length()));
                int NUMBER2 = getNumber(o2.substring(HEAD2.length()));
                return Integer.compare(NUMBER1, NUMBER2);
            }
            return result;
        });
        return files;
    }

    public static int getNumber(String file) {
        String NUMBER = "";
        for (char c : file.toCharArray()) {
            if (!Character.isDigit(c)) {
                break;
            }
            NUMBER += c;
        }
        return Integer.parseInt(NUMBER);
    }
}
