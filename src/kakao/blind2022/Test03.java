package kakao.blind2022;

import java.util.*;
import java.util.Arrays;
import java.util.Date;

public class Test03 {
    public static void main(String[] args) {
     /*   int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        int[] answer = solution(fees, records);*/

        int[] fees = {120, 0, 60, 591};
        String[] records = {"16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"};
        int[] answer = solution(fees, records);
        System.out.println(Arrays.toString(answer));
    }

    static class Car {
        int carNo;
        long inTime;
        long outTime;
        long totalTime;
        String status;

        public Car(int carNo, long inTime, long outTime, long totalTime, String status) {
            this.carNo = carNo;
            this.inTime = inTime;
            this.outTime = outTime;
            this.totalTime = totalTime;
            this.status = status;
        }
    }

    public static int[] solution(int[] fees, String[] records) {
        Map<Integer, Car> carMap = new HashMap<>();

        for (String record : records) {
            String[] strings = record.split(" ");
            String time = strings[0];
            int carNo = Integer.parseInt(strings[1]);
            String status = strings[2];

            if (!carMap.containsKey(carNo)) {
                Car car = new Car(carNo, timeToSecond(time), 0, 0, "IN");
                carMap.put(carNo, car);
            } else {
                if (status.equals("IN")) {
                    Car car = carMap.get(carNo);
                    car.inTime = timeToSecond(time);
                    car.status = "IN";
                    carMap.put(carNo, car);
                } else if (status.equals("OUT")) {
                    Car car = carMap.get(carNo);
                    car.outTime = timeToSecond(time);

                    long inTime = car.inTime;
                    long outTime = car.outTime;

                    long total = car.totalTime + (outTime - inTime);
                    car.totalTime = total;
                    car.status = "OUT";
                    carMap.put(carNo, car);
                }
            }
        }


        int[] answer = new int[carMap.size()];
        int index = 0;
        if (!carMap.isEmpty()) {
            Object[] keys = carMap.keySet().toArray();
            Arrays.sort(keys);

            for (Object key : keys) {
                int totalFee = 0;
                long total;

                Car car = carMap.get(key);
                if (car.status.equals("IN")) {
                    long inTime = car.inTime;
                    long outTime = timeToSecond("23:59");
                    total = car.totalTime + (outTime - inTime);
                }else {
                    total = car.totalTime;
                }

                if (total > fees[0]) { // 기본 시간 초과했으면
                    totalFee += fees[1]; // 기본 요금 부과

                    // 초과 시간 부과
                    long excessTime = (total - fees[0]) / fees[2];
                    if ((total - fees[0]) % fees[2] == 0) {
                        totalFee += excessTime * fees[3];
                    }else {
                        totalFee += (excessTime + 1) * fees[3];
                    }
                }else {
                    totalFee = fees[1];
                }

                answer[index++] = totalFee;
            }
        }

        return answer;
    }

    public static int timeToSecond(String time) {
        String[] list = time.split(":");
        int hour = Integer.parseInt(list[0]);
        int minutes = Integer.parseInt(list[1]);
        return hour * 60 + minutes;
    }
}
