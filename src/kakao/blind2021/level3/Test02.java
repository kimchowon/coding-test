package kakao.blind2021.level3;

import java.text.ParseException;

/**
 * 광고 삽입
 */
public class Test02 {
    public static void main(String[] args) throws ParseException {
/*        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:25:50-00:48:29", "00:40:31-01:00:00", "01:37:44-02:02:30", "01:30:59-01:53:29"};*/

        String play_time = "99:59:59";
        String adv_time = "25:00:00";
        String[] logs = {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};

        String answer = solution(play_time, adv_time, logs);
        System.out.println(answer);
    }

    public static String solution(String play_time, String adv_time, String[] logs) throws ParseException {
        String answer;
        int playSeconds = convertTimeToSeconds(play_time); // 동영상 재생 시간(초로 변환)
        int advSeconds = convertTimeToSeconds(adv_time); // 광고 재생 시간 (초로 변환)

        // 배열 초기화
        int[] secondsList = new int[100 * 60 * 60];

        // 각 로그가 재생되는 초에 카운팅을 해줌
        for (String log : logs) {
            String[] logString = log.split("-");

            // 로그 시작 시간
            String logStartTime = logString[0];
            int logStartSeconds = convertTimeToSeconds(logStartTime);

            // 로그 종료 시간
            String logEndTime = logString[1];
            int logEndSeconds = convertTimeToSeconds(logEndTime);

            for (int i = logStartSeconds; i < logEndSeconds; i++) {
                secondsList[i]++;
            }
        }

        long max = Long.MIN_VALUE;
        int answerSeconds = 0;
        int start = 0;
        int end = advSeconds;
        long totalSeconds = 0;
        while (end <= playSeconds) {
            if (start == 0) {
                for (int i = start; i < end; i++) {
                    totalSeconds += secondsList[i];
                }
            } else {
                totalSeconds = totalSeconds - secondsList[start - 1] + secondsList[end - 1];
            }

            if (max < totalSeconds) {
                max = totalSeconds;
                answerSeconds = start;
            }
            start += 1;
            end += 1;
        }

        answer = convertSecondsToTime(answerSeconds);
        return answer;
    }

    public static int convertTimeToSeconds(String time) {
        String[] timeList = time.split(":");
        int hour = Integer.parseInt(timeList[0]);
        int minutes = Integer.parseInt(timeList[1]);
        int second = Integer.parseInt(timeList[2]);
        return (hour * 3600) + (minutes * 60) + second;
    }

    public static String convertSecondsToTime(int seconds) {
        int hour = seconds / 3600;
        String HH = hour < 10 ? "0" + hour : String.valueOf(hour);
        seconds = seconds % 3600;

        int minutes = seconds / 60;
        String mm = minutes < 10 ? "0" + minutes : String.valueOf(minutes);
        seconds = seconds % 60;

        String ss = seconds < 10 ? "0" + seconds : String.valueOf(seconds);

        return HH + ":" + mm + ":" + ss;
    }
}
