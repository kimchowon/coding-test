package programmers.level3;

import java.util.*;

public class Test16 {
    public static Set<String> answerSet;

    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        //{"frodo", "fradi", "crodo", "abc123", "frodoc"};
        //{"frodo", "fradi", "crodo", "frodoc", "crodoc", "drodoc", "abc123"};
        String[] banner_id = {"fr*d*", "abc1**"};
        //{"fr*d*", "*rodo", "******", "******"};
        //{"fr*d*", "*rodo", "*rodoc", "******"};

        System.out.println("answer=" + solution(user_id, banner_id));
    }

    public static int solution(String[] user_id, String[] banned_id) {
        answerSet = new HashSet<>();

        Set<String>[] setArray = new HashSet[banned_id.length];
        for (int i = 0; i < setArray.length; i++) {
            setArray[i] = new HashSet<>();
            String b_id = banned_id[i];
            for (int k = 0; k < user_id.length; k++) {
                String u_id = user_id[k];
                if (isMapping(u_id, b_id)) {
                    setArray[i].add(u_id);
                }
            }
        }

        List<String> stringList = new ArrayList<>();
        Set<String> tempSet = new HashSet<>();
        dfs(setArray, 0, stringList);


        return answerSet.size();
    }

    public static boolean isMapping(String user_id, String banner_id) {
        if (user_id.length() == banner_id.length()) {
            for (int i = 0; i < user_id.length(); i++) {
                if (banner_id.charAt(i) == '*') {
                    continue;
                }

                if (user_id.charAt(i) != banner_id.charAt(i)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static void dfs(Set<String>[] setArray, int index, List<String> stringList) {
        if (index == setArray.length) {
            Collections.sort(stringList);
            String string = stringList.toString();
            answerSet.add(string);
            return;
        }

        for (String s : setArray[index]) {
            if (stringList.contains(s)) {
                continue;
            }
            stringList.add(s);
            dfs(setArray, index + 1, stringList);
            stringList.remove(s);
        }
    }
}
