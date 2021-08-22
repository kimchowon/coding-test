package programmers.level3;

public class Test00 {
    public static void main(String[] args) {
        int a = 10;
        int b = 15;

        String binary_a = Integer.toBinaryString(a);
        String binary_b = Integer.toBinaryString(b);

        System.out.println(binary_a);
        System.out.println(binary_b);
        System.out.println(Integer.toBinaryString(a & b));
        System.out.println(Integer.toBinaryString(a | b));
        System.out.println(Integer.toBinaryString(a ^ b));
        System.out.println(Integer.toBinaryString(~a));
        System.out.println(Integer.toBinaryString(a << 2));
        System.out.println(Integer.toBinaryString(a >> 2));



    }
}
