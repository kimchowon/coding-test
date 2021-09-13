package kakao.blind2021.level3;

public class Test04 {
    public static void main(String[] args) {
        String play_time = "99:59:59";
        String adv_time = "25:00:00";
        String[] logs = {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};
        String answer = solution(play_time, adv_time, logs);
        System.out.println(answer);
    }

    public static String solution(String play_time, String adv_time, String[] logs) {
        int playSec = timeToSecond(play_time);
        int advSec = timeToSecond(adv_time);

        int[] secondArr = new int[100 * 3600];
        for (String log : logs) {
            String[] timeList = log.split("-");

            int startSec = timeToSecond(timeList[0]);
            int endSec = timeToSecond(timeList[1]);

            for (int i = startSec; i < endSec; i++) {
                secondArr[i]++;
            }
        }

        long max = Long.MIN_VALUE;
        int answerSeconds = 0;
        int start = 0;
        int end = advSec;
        long totalSeconds = 0;
        while (end <= playSec) {
            if (start == 0) {
                for (int i = start; i < end; i++) {
                    totalSeconds += secondArr[i];
                }
            } else {
                totalSeconds = totalSeconds - secondArr[start - 1] + secondArr[end - 1];
            }

            if (max < totalSeconds) {
                max = totalSeconds;
                answerSeconds = start;
            }
            start += 1;
            end += 1;
        }

        String answer = secToTime(answerSeconds);
        return answer;
    }

    public static int timeToSecond(String time) {
        String[] list = time.split(":");
        int hour = Integer.parseInt(list[0]);
        int minutes = Integer.parseInt(list[1]);
        int sec = Integer.parseInt(list[2]);
        return hour * 3600 + minutes * 60 + sec;
    }

    public static String secToTime(int seconds) {
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
