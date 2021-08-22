package programmers.level3;

import java.util.HashSet;
import java.util.Set;

public class Test15 {
    public static void main(String[] args) {
        String dirs = //"ULURRDLLU";
                "LULLLLLLU";
        solution(dirs);
    }

    public static int solution(String dirs) {
        int answer = 0;
        Set<String> set = new HashSet<>();

        int[] start = {0, 0};
        int[] end = new int[2];
        for (int i = 0; i < dirs.length(); i++) {
            char dir = dirs.charAt(i);

            int endX, endY;
            if (dir == 'U') {
                endX = start[0];
                endY = start[1] + 1;
            } else if (dir == 'D') {
                endX = start[0];
                endY = start[1] - 1;
            } else if (dir == 'L') {
                endX = start[0] -1;
                endY = start[1];
            } else {
                endX = start[0] +1;
                endY = start[1];
            }

            if (checkExceedBoundary(endX, endY)) {
                continue;
            }else {
                end[0] = endX;
                end[1] = endY;
            }

            String startStr = start[0] + "" + start[1];
            String endStr = end[0] + "" + end[1];

            if(set.add(startStr + endStr) && set.add(endStr + startStr)) {
                answer++;
            }

            start[0] = end[0];
            start[1] = end[1];
        }
        return answer;
    }

    public static boolean checkExceedBoundary(int x, int y) {
        if (x < -5 || x > 5 || y < -5 || y > 5) {
            return true;
        }
        return false;
    }
}
