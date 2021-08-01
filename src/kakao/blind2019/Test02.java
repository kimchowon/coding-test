package kakao.blind2019;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test02 {
    public static void main(String[] args) {
        int[] food_times =// {3,5,1,6,5,3};
        {3,1,2};
        long k =
                5;
       // 20;

        int answer = solution(food_times, k);
        System.out.println(answer);
    }

    public static class FoodTimeInfo{
        int order; // 몇번째 음식인지
        int time; // 먹는데 필요한 시간

        public FoodTimeInfo(int order, int time) {
            this.order = order;
            this.time = time;
        }
    }

    public static int solution(int[] food_times, long k) {
        int answer = 0;

        List<FoodTimeInfo> foodTimeInfoList = new ArrayList<>();
        for (int i = 0; i < food_times.length; i++) {
            foodTimeInfoList.add(new FoodTimeInfo(i + 1, food_times[i]));
        }

        // 시간으로 정렬
        Collections.sort(foodTimeInfoList, Comparator.comparingInt(o -> o.time));

        int height = foodTimeInfoList.get(0).time; // 세로 - 시간
        int width = foodTimeInfoList.size(); // 가로 - 음식 개수
        int time = 0, count = 0;
        while (time + count<= k) {
            time = time + count;
            if (height > 0) {
                count = height * width;
            }

            if (foodTimeInfoList.size() > 1) {
                height = foodTimeInfoList.get(1).time - foodTimeInfoList.get(0).time;
                foodTimeInfoList.remove(0);
                width = foodTimeInfoList.size();
            }
        }

        // 남은 배열을 원래 순서대로 정렬
        Collections.sort(foodTimeInfoList, Comparator.comparingInt(o -> o.order));

        answer = (int)(k - time + 1) % foodTimeInfoList.size();
        answer = answer == 0 ? 0 : answer - 1;
        return foodTimeInfoList.get(answer).order;
    }
}
