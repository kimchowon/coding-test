package programmers.level2;

/**
 * 124 나라의 숫자
 */
public class Test01 {
    static int count = 0;
    static String answer = "";

    public static void main(String[] args) {
        System.out.println("answer : " + solution(13));
    }

    public static class Power {
        double quotient;
        double remainder;

        public Power(double quotient, double remainder) {
            this.quotient = quotient;
            this.remainder = remainder;
        }
    }

    public static String solution(int n) {
        Power power = getPower(n, 3, 1);

        int[] items = {1, 2, 4};
        int[] buckets = new int[(int) power.quotient];
        repeatPermutation(items, buckets, buckets.length, (int) power.remainder);
        return answer;
    }

    public static Power getPower(int n, int k, int e) {
        double power = 0;
        for (int i = 1; i <= e; i++) {
            power += Math.pow(k, i);
        }

        if (power < n && (n - power) < Math.pow(k, e + 1)) {
            return new Power(e + 1, (n - power));
        }

        return getPower(n, k, e + 1);
    }

    public static void repeatPermutation(int[] items, int[] buckets, int k, int order) {
        if (k == 0) {
            count++;
            if (order == count) {
                for (int i = 0; i < buckets.length; i++) {
                    answer += String.valueOf(buckets[i]);
                }
            }
            return;
        }

        int lastIndex = buckets.length - k - 1;
        for (int i = 0; i < items.length; i++) {
            buckets[lastIndex + 1] = items[i];
            repeatPermutation(items, buckets, k - 1, order);
        }
    }
}
