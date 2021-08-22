package kakaoTest.one;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * 2018 KAKAO BLIND RECRUITMENT
 * 방금 그곡
 * 소요시간: 46분 34초
 */
public class Test02 {
    static class MusicInfo implements Comparable<MusicInfo> {
        long minutes;
        String title;
        String melody;

        public MusicInfo(long minutes, String title, String melody) {
            this.minutes = minutes;
            this.title = title;
            this.melody = melody;
        }

        @Override
        public int compareTo(MusicInfo o) {
            if (o.minutes > this.minutes) {
                return 1;
            } else if (this.minutes > o.minutes) {
                return -1;
            }
            return 1;
        }
    }

    public static void main(String[] args) throws ParseException {
        String m = "ABC";
        String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String answer = solution(m, musicinfos);
        System.out.println(answer);
    }

    public static String solution(String m, String[] musicinfos) throws ParseException {
        String answer = "";

        ArrayList<MusicInfo> musicInfoArrayList = new ArrayList<>();
        for (String musicInfo : musicinfos) {
            String[] infos = musicInfo.split(",");
            long minutes = getMinutes(infos[0], infos[1]);
            String title = infos[2];
            String melody = replaceMelody(infos[3]);
            MusicInfo music = new MusicInfo(minutes, title, melody);
            musicInfoArrayList.add(music);
        }
        Collections.sort(musicInfoArrayList);

        m = replaceMelody(m);
        for (MusicInfo musicInfo : musicInfoArrayList) {
            long minutes = musicInfo.minutes;
            String melody = getEntireMelody(minutes, musicInfo.melody);

            if (melody.contains(m)) {
                answer = musicInfo.title;
                break;
            }
        }

        if (answer.equals("")) {
            answer = "(None)";
        }
        return answer;
    }

    public static long getMinutes(String start, String end) throws ParseException {
        DateFormat f = new SimpleDateFormat("hh:mm");
        Date d1 = f.parse(start);
        Date d2 = f.parse(end);
        long difference = d1.getTime() - d2.getTime(); // milliseconds
        long minutes = difference / 60000;
        return Math.abs(minutes);
    }

    public static String replaceMelody(String melody) {
        melody = melody.replaceAll("C#", "c");
        melody = melody.replaceAll("D#", "d");
        melody = melody.replaceAll("F#", "f");
        melody = melody.replaceAll("G#", "g");
        melody = melody.replaceAll("A#", "a");
        return melody;
    }

    public static String getEntireMelody(long minutes, String melody) {
        String entireMelody = "";
        int count = 0;
        int index = 0;
        while (count <= minutes) {
            count++;

            if (index == melody.length()) {
                index = 0;
            }
            entireMelody += melody.charAt(index);
            index++;
        }
        return entireMelody;
    }
}
