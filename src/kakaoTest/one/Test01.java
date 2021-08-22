package kakaoTest.one;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018 KAKAO BLIND RECRUITMENT
 * 뉴스 클러스터링
 * 소요시간: 1시간 7분 39초
 */
public class Test01 {
    public static void main(String[] args) {

        String str1 = " fdfdf ";
        String str2 = "  ";

        int answer = solution(str1, str2);
        System.out.println(answer);
    }

    public static int solution(String str1, String str2) {
        double answer;
        List<String> str1List = makeStringList(str1.toLowerCase());
        List<String> str2List = makeStringList(str2.toLowerCase());

        if (str1List.size() == 0 && str2List.size() == 0) {
            return 65536;
        } else {
            int intersection = 0;
            for (String str : str1List) {
                if (str2List.contains(str)) {
                    intersection++;
                    str2List.remove(str);
                }
            }
            int union = intersection + (str1List.size() - intersection) + str2List.size();
            answer = intersection / (double) union;
            answer *= 65536;
        }
        return (int) answer;
    }

    public static boolean isAlphabet(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((65 > c || c > 90) && (97 > c || c > 122)) {
                return false;
            }
        }
        return true;
    }

    public static List<String> makeStringList(String str) {
        List<String> strList = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            String s = str.charAt(i) + "" + str.charAt(i + 1);
            if (isAlphabet(s)) {
                strList.add(s);
            }
        }
        return strList;
    }
}
