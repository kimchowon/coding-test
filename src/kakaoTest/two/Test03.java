package kakaoTest.two;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 문제: 셔틀버스
 * 소요시간: 49분 34초
 */
public class Test03 {
    public static void main(String[] args) throws ParseException {

        int n = 1;
        int t = 1;
        int m = 1;
        String[] timetable = {"23:59"};

        String answer = solution(n, t, m, timetable);
        System.out.println(answer);
    }

    public static String solution(int n, int t, int m, String[] timetable) throws ParseException {
        Date answerDate = null;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");

        PriorityQueue<Date> pq = new PriorityQueue<>((o1, o2) -> o1.getTime() >= o2.getTime() ? 1 : -1);
        for (String time : timetable) {
            Date date = df.parse(time);
            pq.add(date);
        }

        String startTime = "09:00";
        Date date = df.parse(startTime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        List<Date> bus = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Date curTime = cal.getTime();

            bus.clear();
            while (!pq.isEmpty() && bus.size() < m) {
                Date person = pq.peek();
                if (person.getTime() <= curTime.getTime()) {
                    bus.add(pq.poll()); // 버스에 탑승, queue에서 제거
                } else {
                    break;
                }
            }

            // 현재 버스가 마지막 버스이면 
            if (i == n - 1) {
                if (bus.size() < m) { // 버스에 자리가 남으면
                    answerDate = curTime;
                    break;
                } else if (bus.size() == m) { // 버스가 만석이면
                    Date lastPerson = bus.get(bus.size() - 1); // 마지막에 탑승한 사람
                    Calendar answerCal = Calendar.getInstance();
                    answerCal.setTime(lastPerson);
                    answerCal.add(Calendar.MINUTE, -1);
                    answerDate = answerCal.getTime();
                }
            }
            // 시간 더하기(t분)
            cal.add(Calendar.MINUTE, t);
        }
        return df.format(answerDate);
    }
}
