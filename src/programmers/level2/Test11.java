package programmers.level2;

public class Test11 {
    public static int answer = 0;

    public static void main(String[] args) {
        int n = 2;
     //   String[] data = {"N~F=0", "R~T>2"};
        String[] data = {"M~C<2", "C~M>1"};

        System.out.println("answer : " + solution(n, data));
    }

    public static int solution(int n, String[] data) {
        answer = 0;

        char[] items = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        char[] buckets = new char[items.length];

        permutation(items, buckets, buckets.length, data);
        return answer;
    }

    public static void permutation(char[] items, char[] buckets, int k, String[] data) {
        if (k == 0) {
            String str = String.valueOf(buckets);

            boolean flag = true;
            for (int i = 0; i < data.length; i++) {
                if (!discriminate(str,data[i])) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                answer++;
            }
            return;

        }

        int lastIndex = buckets.length - k - 1;
        for (int i = 0; i < items.length; i++) {
            int flag = 0;

            for (int j = 0; j <= lastIndex; j++) {
                if (buckets[j] == items[i]) {
                    flag = 1;
                }
            }

            if (flag == 1) {
                continue;
            }

            buckets[lastIndex + 1] = items[i];
            permutation(items, buckets, k - 1, data);
        }

    }

    public static boolean discriminate(String lines, String condition) {
        String party = condition.charAt(0) + "";
        String opponent = condition.charAt(2) + "";
        String sign = condition.charAt(3) + "";
        int interval = Integer.parseInt(condition.charAt(4) + "");

        int end = Math.max(lines.indexOf(party), lines.indexOf(opponent));
        int start = Math.min(lines.indexOf(party), lines.indexOf(opponent));

        if (sign.equals("=")) {
            if ((Math.abs(end - start - 1)) == interval) {
                return true;
            }
        }

        if (sign.equals(">")) {
            if ((Math.abs(end - start - 1)) > interval) {
                return true;
            }

        }

        if (sign.equals("<")) {
            if ((Math.abs(end - start - 1)) < interval) {
                return true;
            }
        }

        return false;
    }

}
