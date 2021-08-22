package programmers.level2;

import java.util.*;

public class Test32 {
    public static void main(String[] args) {
        String str1 = "handshake";
        String str2 = "shake hands";

        System.out.println("answer : " + solution(str1, str2));
    }

    public static int solution(String str1, String str2) {
        final int DEFAULT_NUM = 65536;

        List<String> list1 = getStrArray(str1);
        List<String> list2 = getStrArray(str2);

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        Set<String> set = new HashSet<>();

        for (String s1 : list1) {
            map1.put(s1, map1.getOrDefault(s1, 0) + 1);
            set.add(s1);
        }

        for (String s2 : list2) {
            map2.put(s2, map2.getOrDefault(s2, 0) + 1);
            set.add(s2);
        }

        if (map1.isEmpty() && map2.isEmpty()) {
            return DEFAULT_NUM;
        }

        int interIndex = 0;
        int unionIndex = 0;
        for (String s : set) {
            int num1 = map1.getOrDefault(s, 0);
            int num2 = map2.getOrDefault(s, 0);

            if (num1 != 0 && num2 != 0) {
                interIndex += Math.min(num1, num2);
                unionIndex += (Math.max(num1, num2) - Math.min(num1, num2));
            } else {
                unionIndex += num1 + num2;
            }
        }

        double answer = interIndex / (double) (unionIndex + interIndex);
        answer = answer * DEFAULT_NUM;
        return (int) answer;
    }

    public static List<String> getStrArray(String str) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            int ascii = str.charAt(i);
            String s = "";
            if ((65 <= ascii && ascii <= 90) || (ascii >= 97 && ascii <= 122)) {
                s += str.charAt(i) + "";
            } else {
                continue;
            }

            ascii = str.charAt(i + 1);
            if ((65 <= ascii && ascii <= 90) || (ascii >= 97 && ascii <= 122)) {
                s += str.charAt(i + 1) + "";
            } else {
                continue;
            }

            stringList.add(s.toLowerCase());
        }
        return stringList;
    }

    /*public static List<String> getStrArray(String str) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            String s = "";
            String c = str.charAt(i)+"";
            if (Pattern.matches("^[a-zA-Z]*$", c)) {
                s+=c;
            }else {
                continue;
            }

            c = str.charAt(i+1)+"";
            if (Pattern.matches("^[a-zA-Z]*$", c)) {
                s+=c;
            }else {
                continue;
            }

            stringList.add(s.toLowerCase());
        }
        return stringList;
    }*/
}
