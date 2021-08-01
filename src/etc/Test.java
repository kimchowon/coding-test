package etc;

import java.util.Arrays;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        for (int i = 0; i < nodeinfo.length; i++) {
            System.out.print(Arrays.toString(nodeinfo[i]) + " ");
        }
        System.out.println();

        // x좌표 기준으로 오름차순 정렬
        Arrays.sort(nodeinfo, Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < nodeinfo.length; i++) {
            System.out.print(Arrays.toString(nodeinfo[i])+ " ");
        }
        System.out.println();

        // x좌표 기준으로 내림차순 정렬
        Arrays.sort(nodeinfo, (o1, o2) -> -Integer.compare(o1[0], o2[0]));
        for (int i = 0; i < nodeinfo.length; i++) {
            System.out.print(Arrays.toString(nodeinfo[i])+ " ");
        }
        System.out.println();

        // y좌표 기준으로 내림차순 정렬
        // y좌표가 같다면 x좌표 기준으로 오름차순 정렬
        Arrays.sort(nodeinfo, (o1, o2) -> {
            int result = Integer.compare(o1[1], o2[1]);
            if (result == 0) {
                return Integer.compare(o1[0], o2[0]);
            }else {
                return  -result;
            }
        });
        for (int i = 0; i < nodeinfo.length; i++) {
            System.out.print(Arrays.toString(nodeinfo[i])+ " ");
        }
        System.out.println();

    }
}
