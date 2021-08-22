package kakaoTest.one;

import java.util.ArrayList;
import java.util.List;

public class Test01_re {
    public static void main(String[] args) {
        String str1 = "FRANCE";
        String str2 = "french";
        int answer = solution(str1, str2);
        System.out.println(answer);
    }

    public static int solution(String str1, String str2) {
        List<String> str1List = makeStringList(str1.toLowerCase());
        List<String> str2List = makeStringList(str2.toLowerCase());

        if (str1List.size() == 0 && str2List.size() == 0) {
            return 65536;
        } else {
            int intersection = 0;
            for (String s : str1List) {
                if (str2List.contains(s)) {
                    intersection++;
                    str2List.remove(s);
                }
            }

            int union = intersection + (str2List.size()) + (str1List.size() - intersection);
            int answer = (int) ((intersection / (double) union) * 65536);
            return answer;
        }
    }

    public static List<String> makeStringList(String str) {
        List<String> strList = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            String s = str.charAt(i) + "" + str.charAt(i + 1);
            if (isAllAlphabet(s)) {
                strList.add(s);
            }
        }
        return strList;
    }

    public static boolean isAllAlphabet(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((65 > c || c > 90) && (97 > c || c > 122)) {
                return false;
            }
        }
        return true;
    }
}
