package kakaoTest.three;

import java.util.Arrays;

/**
 * 파일명 정렬
 * 소요시간: 25분 3초
 */
public class Test02 {
    public static void main(String[] args) {
        String[] files =// {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
                {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};

        String[] answers = solution(files);
        System.out.println(Arrays.toString(answers));
    }

    public static String[] solution(String[] files) {
        Arrays.sort(files, (o1, o2) -> {
            String HEAD1 = getHead(o1.toLowerCase());
            String HEAD2 = getHead(o2.toLowerCase());

            int result = HEAD1.compareTo(HEAD2);
            if (result == 0) {
                int NUMBER1 = getNumber(o1.substring(HEAD1.length()));
                int NUMBER2 = getNumber(o2.substring(HEAD2.length()));
                return Integer.compare(NUMBER1, NUMBER2);
            }
            return result;
        });
        return files;
    }

    public static String getHead(String file) {
        String HEAD = "";
        for (char c : file.toCharArray()) {
            if (48 <= c && c <= 57) {
                break;
            }
            HEAD += c;
        }
        return HEAD;
    }

    public static int getNumber(String file) {
        String NUMBER = "";
        for (char c : file.toCharArray()) {
            if (48 > c || c > 57) {
                break;
            }
            NUMBER += c;
        }
        return Integer.parseInt(NUMBER);
    }
}
