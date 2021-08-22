package kakaoTest.one;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Test03 {
    static class Log implements Comparable<Log> {
        int startTime;
        int endTime;

        public Log(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Log o) {
            if (this.startTime >= o.startTime) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) throws ParseException {
        String[] lines =// {"2016-09-15 03:10:33.020 0.011s"};

                {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s",
                        "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s",
                        "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s",
                        "2016-09-15 21:00:02.066 2.62s"};

        System.out.println(solution(lines));
    }

    public static int solution(String[] lines) throws ParseException {
        int answer = Integer.MIN_VALUE;
        int startTime = Integer.MAX_VALUE;

        List<Log> logList = new ArrayList<>();
        for (String line : lines) {
            String[] strings = line.split(" ");
            double T = Double.parseDouble(strings[2].substring(0, strings[2].length() - 1)); // 처리 시간
            int endTimeMilliSeconds = getTotalMilliSeconds(strings[1]);
            int startTimeMilliSeconds = endTimeMilliSeconds - (int)(T * 1000) + 1;

            startTime = Math.min(startTimeMilliSeconds, startTime);
            Log log = new Log(startTimeMilliSeconds, endTimeMilliSeconds);
            logList.add(log);
        }

        int realEndTime = logList.get(logList.size() - 1).endTime;

        while (startTime <= realEndTime) {
            int count = 0;
            int endTime = startTime + 1000;

            for (int i = 0; i < logList.size(); i++) {
                Log log = logList.get(i);
                if (startTime <= log.endTime && log.endTime <= endTime) {
                    count++;
                    continue;
                } else if (startTime <= log.startTime && log.startTime <= endTime) {
                    count++;
                    continue;
                } else if (startTime <= log.startTime && log.endTime <= endTime) {
                    count++;
                    continue;
                } else if (log.startTime <= startTime && endTime <= log.endTime) {
                    count++;
                    continue;
                }
            }

            answer = Math.max(answer, count);
            startTime = endTime;
        }
        return answer;
    }

    public static int getTotalMilliSeconds(String time) {
        String[] timeStrings = time.split(":");
        int hour = Integer.parseInt(timeStrings[0]);
        int minutes = Integer.parseInt(timeStrings[1]);
        double seconds = Double.parseDouble(timeStrings[2]);
        return (hour * 3600 * 1000) + (minutes * 60 * 1000) + (int)(seconds * 1000);
    }
}
