package programmers.level3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * JONNA JONNA JONNA 어렵워.. SIBAL...
 * 이건 패스하고 다른 문제 풀자... 와... SIBAL...
 */
public class Test01 {
    public static void main(String[] args) throws ParseException {
        String[] lines =// {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"};
                {"2016-09-15 20:59:57.421 0.351s",
                        "2016-09-15 20:59:58.233 1.181s",
                        "2016-09-15 20:59:58.299 0.8s",
                        "2016-09-15 20:59:58.688 1.041s",
                        "2016-09-15 20:59:59.591 1.412s",
                        "2016-09-15 21:00:00.464 1.466s",
                        "2016-09-15 21:00:00.741 1.581s",
                        "2016-09-15 21:00:00.748 2.31s",
                        "2016-09-15 21:00:00.966 0.381s",
                        "2016-09-15 21:00:02.066 2.62s"};
        System.out.println(solution(lines));

    }

    public static int solution(String[] lines) throws ParseException {
        int answer = 0;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        for (int i = 0; i < lines.length; i++) {
            String[] lineDivide = lines[i].split(" ");
            String endDtStr = lineDivide[0] + " " + lineDivide[1];
            double second = Double.parseDouble(lineDivide[2].substring(0, lineDivide[2].length() - 1)) - 0.001;

            Date endDt = dateFormat.parse(endDtStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(endDt);

            cal.add(Calendar.MILLISECOND, (int) (-second * 1000));

            String startDtStr = dateFormat.format(cal.getTime());
            lines[i] = startDtStr + " " + lines[i];
        }

        for (int i = 0; i < lines.length; i++) {
            int count = 0;
            String curLineDivide[] = lines[i].split(" ");
            String startDtStr = curLineDivide[0] + " " + curLineDivide[1];
            String endDtStr = curLineDivide[2] + " " + curLineDivide[3];

            Date curStartDt = dateFormat.parse(startDtStr);
            long curStartDtMilliSecond = curStartDt.getTime();

            Date curEndDt = dateFormat.parse(endDtStr);
            long curEndDtMilliSecond = curEndDt.getTime();

            for (int j = 0; j < lines.length; j++) {
                String nextLineDivide[] = lines[j].split(" ");
                String nextStartDtStr = nextLineDivide[0] + " " + nextLineDivide[1];
                String nextEndDtStr = nextLineDivide[2] + " " + nextLineDivide[3];

                Date nextStartDt = dateFormat.parse(nextStartDtStr);
                long nextStartDtMilliSecond = nextStartDt.getTime();

                Date nextEndDt = dateFormat.parse(nextEndDtStr);
                long nextEndDtMilliSecond = nextEndDt.getTime();

                if (nextStartDtMilliSecond <= curStartDtMilliSecond && curStartDtMilliSecond <= nextEndDtMilliSecond) {
                    count++;
                    continue;
                }

                if (nextEndDtMilliSecond <= curEndDtMilliSecond && curEndDtMilliSecond <= nextEndDtMilliSecond) {
                    count++;
                    continue;
                }

                if (curStartDtMilliSecond <= nextStartDtMilliSecond && nextEndDtMilliSecond <= curStartDtMilliSecond) {
                    count++;
                    continue;
                }

                if (nextStartDtMilliSecond <= curStartDtMilliSecond && curEndDtMilliSecond <= nextEndDtMilliSecond) {
                    count++;
                    continue;
                }
            }
            answer = Math.max(answer, count);
        }
        return answer;
    }
}
