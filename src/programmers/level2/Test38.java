package programmers.level2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class Test38 {
    public static void main(String[] args) throws ParseException {
        String m = "ABCDEFG";
        String[] musicinInfo = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};

        System.out.println(solution(m, musicinInfo));
    }

    // 플레이 리스트 정렬
    // 1. 재생시간이 긴 순서 (내림차순)
    // 2. 재싱시간이 같다면 먼저 입력된 순서대로(오름차순)
    public static class MusicComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {

            String[] musicInfo1 = o1.split(",");
            String[] musicInfo2 = o2.split(",");
            long playTime1 = 0;
            long playTime2 = 0;
            try {
                playTime1 = getPlayTime(musicInfo1[0], musicInfo1[1]);
                playTime2 = getPlayTime(musicInfo2[0], musicInfo2[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (playTime2 > playTime1) {
                return 1;
            } else if (playTime1 > playTime2) {
                return -1;
            }
            return 1;
        }
    }

    public static String solution(String m, String[] musicinfos) throws ParseException {
        MusicComparator musicComparator = new MusicComparator();
        Arrays.sort(musicinfos, musicComparator);

        for (int i = 0; i < musicinfos.length; i++) {
            String[] musicinStr = musicinfos[i].split(",");

            long playTime = getPlayTime(musicinStr[0], musicinStr[1]);  // 재생시간
            String melody = convertMelody(musicinStr[3]);  // 멜로디 (#이 붙은 음들은 소문자로 치환)

            int count = 0; // 재싱시간만큼 음을 이어붙이는 숫자를 세는 카운트
            String resultStr = ""; // 음을 이어붙이는 변수
            while (count < playTime) {
                for (int j = 0; j < melody.length(); j++) {
                    if (count == playTime) {
                        break;
                    }

                    resultStr += melody.charAt(j);
                    count++;
                }
            }
            if (resultStr.contains(convertMelody(m))) {
                return musicinStr[2];
            }
        }
        return "(None)";
    }

    public static long getPlayTime(String startTime, String endTime) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date d1 = dateFormat.parse(startTime);
        Date d2 = dateFormat.parse(endTime);

        long diff = Math.abs(d2.getTime() - d1.getTime());
        long min = diff / (1000 * 60);
        return min;
    }

    public static String convertMelody(String melody) {
        //C, C#, D, D#, E, F, F#, G, G#, A, A#, B
        melody = melody.replaceAll("C#", "c");
        melody = melody.replaceAll("D#", "d");
        melody = melody.replaceAll("F#", "f");
        melody = melody.replaceAll("G#", "g");
        melody = melody.replaceAll("A#", "a");

        return melody;
    }
}
