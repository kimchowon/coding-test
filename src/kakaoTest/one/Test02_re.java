package kakaoTest.one;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Test02_re {
    public static void main(String[] args) throws ParseException {
        String m = "ABC";
        String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String answer = solution(m, musicinfos);
        System.out.println(answer);
    }

    static class MusicInfoDto implements Comparable<MusicInfoDto> {
        long playTime;
        String title;
        String melody;

        public MusicInfoDto(long playTime, String title, String melody) {
            this.playTime = playTime;
            this.title = title;
            this.melody = melody;
        }

        @Override
        public int compareTo(MusicInfoDto o) {
            if (o.playTime > this.playTime) {
                return 1;
            } else if (this.playTime > o.playTime) {
                return -1;
            }
            return 1;
        }
    }

    public static String solution(String m, String[] musicinfos) throws ParseException {
        String answer = "";

        List<MusicInfoDto> musicInfoDtoList = new ArrayList<>();
        for (String musicInfo : musicinfos) {
            String[] strings = musicInfo.split(",");
            long playTime = getPlayTime(strings[0], strings[1]); // 재생 시간
            String title = strings[2]; // 노래 제목
            String melody = replaceMelody(strings[3]); // 멜로디

            MusicInfoDto musicInfoDto = new MusicInfoDto(playTime, title, melody);
            musicInfoDtoList.add(musicInfoDto);

        }
        Collections.sort(musicInfoDtoList);
        m = replaceMelody(m);
        for (MusicInfoDto musicInfoDto : musicInfoDtoList) {
            long playTime = musicInfoDto.playTime;
            String melody = getEntireMelody(playTime, musicInfoDto.melody);

            if (melody.contains(m)) {
                answer = musicInfoDto.title;
                break;
            }
        }

        if (answer.equals("")) {
            answer = "(None)";
        }
        return answer;
    }

    public static long getPlayTime(String start, String end) throws ParseException {
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

    public static String getEntireMelody(long playTime, String melody) {
        String entireMelody = "";
        int time = 0;
        int index = 0;
        while (time <= playTime) {
            time++;

            if (index == melody.length()) {
                index = 0;
            }
            entireMelody += melody.charAt(index);
            index++;
        }
        return entireMelody;
    }
}
