package kakaoTest.three;

import java.util.LinkedList;
import java.util.Queue;

public class Test05 {
    public static void main(String[] args) {
        int cacheSize = 2;
        String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"};

        int answer = solution(cacheSize, cities);
        System.out.println(answer);
    }

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> queue = new LinkedList<>();

        if (cacheSize == 0) {
            return cities.length * 5;
        }

        for (String city : cities) {
            city = city.toLowerCase();

            if (!queue.contains(city)) {
                answer = answer + 5;
                if (queue.size() == cacheSize) {
                    queue.poll();
                }
            } else {
                answer = answer + 1;
                queue.remove(city);
            }
            queue.add(city);
        }
        return answer;
    }
}
