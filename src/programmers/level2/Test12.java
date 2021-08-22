package programmers.level2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Test12 {
    public static void main(String[] args) throws ParseException {
        String date = "T01:00:04.003";

        SimpleDateFormat dtFormat = new SimpleDateFormat("THH:mm:ss.SSS");
        LocalDateTime dt = LocalDateTime.parse(date);

        System.out.println();


    }
}
