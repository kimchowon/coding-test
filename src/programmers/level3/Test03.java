package programmers.level3;

public class Test03 {
    public static void main(String[] args) {

        int[] a = {-16, 27, 65, -2, 58, -92, -71, -68, -61, -33};
        //{9, -1, -5};
        System.out.println(solution(a));
    }

    public static int solution(int[] a) {
        int answer = 0;

        int[] leftMin = new int[a.length];
        int[] rightMin = new int[a.length];

        int minValue = Integer.MAX_VALUE;
        for (int i=1; i < a.length; i++) {
            if (minValue > a[i-1]) {
                minValue = a[i-1];
            }
            leftMin[i] = minValue;
        }

        minValue = Integer.MAX_VALUE;
        for (int i=a.length-2; i >=0; i--) {
            if (minValue > a[i+1]) {
                minValue = a[i+1];
            }
            rightMin[i] = minValue;
        }

        for (int i=0; i < a.length; i++) {
            if (i==0 || i==a.length-1) {
                answer++;
                continue;
            }

            if (leftMin[i] < a[i] && rightMin[i] < a[i]) {
                continue;
            }
            answer++;
        }

        return answer;
    }
}
