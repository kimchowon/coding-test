package programmers.level2;

import java.util.Arrays;
import java.util.Comparator;

public class Test39 {
    public static void main(String[] args) {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        //{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        solution(files);
    }

    public static String[] solution(String[] files) {
        String[] answer = {};

        FileComparator fileComparator = new FileComparator();
        Arrays.sort(files, fileComparator);

        return files;
    }

    public static class FileComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            String head1 = getHead(o1).toLowerCase();
            String head2 = getHead(o2).toLowerCase();

            int result = head1.compareTo(head2);

            if (result == 0) {
                int num1 = getNumber(o1);
                int num2 = getNumber(o2);

                if (num1 > num2) {
                    return 1;
                } else if (num1 < num2) {
                    return -1;
                }
                return 0;
            }
            return result;
        }
    }

    public static String getHead(String fileName) {
        String head = "";
        for (int i = 0; i < fileName.length(); i++) {
            char c = fileName.charAt(i);
            if (48 <= c && c <= 57) {
                break;
            }
            head += c;
        }
        return head;
    }

    public static int getNumber(String fileName) {
        String number = "";
        String head = getHead(fileName);
        int headIndexOf = fileName.indexOf(head);
        for (int i = headIndexOf + head.length(); i < fileName.length(); i++) {
            char c = fileName.charAt(i);
            if (48 > c || c > 57) {
                break;
            }
            number += c;
        }
        return Integer.parseInt(number);
    }
}
