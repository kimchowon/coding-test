package baekjoon.gold5;

import java.io.*;
import java.util.*;

public class Test12 {
    static class Gear { // 톱니
        int[] wheels; // 바퀴
        boolean isRotate; // 회전 여부
        int rotateDir; // 회전 방향

        public Gear(int[] wheels, boolean isRotate, int rotateDir) {
            this.wheels = wheels;
            this.isRotate = isRotate;
            this.rotateDir = rotateDir;
        }
    }

    public static Gear[] gears = new Gear[5];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 1; i <= 4; i++) {
            int[] wheels = new int[8];
            String[] strings = br.readLine().split("");
            for (int j = 0; j < strings.length; j++) {
                wheels[j] = Integer.parseInt(strings[j]);
            }
            gears[i] = new Gear(wheels, false, 0);
        }

        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int rotateDir = Integer.parseInt(st.nextToken());
            initGearInfo(order, rotateDir);
            rotateGears();
        }

        int answer = 0;
        for (int i=1; i < gears.length; i++) {
            Gear gear = gears[i];
            int num = gear.wheels[0];

            if (num == 1) {// s극 이면
                answer += Math.pow(2, i-1);
            }
        }

        bw.write(answer + "\n");

        br.close();
        bw.flush();
        bw.close();
    }

    public static void initGearInfo(int order, int rotateDir) {
        // 현재 톱니 관련 정보 초기화
        gears[order].isRotate = true;
        gears[order].rotateDir = rotateDir;

        // 현재 톱니 왼쪽 탐색
        for (int i = order - 1; i >= 1; i--) {
            Gear beforeGear = gears[i]; // 왼쪽 톱니
            Gear curGear = gears[i + 1]; // 현재 톱니

            if (curGear.isRotate) {
                // 톱니 바퀴 극이 다르면 (n, s)
                if (beforeGear.wheels[2] != curGear.wheels[6]) {
                    beforeGear.isRotate = true;
                    beforeGear.rotateDir = 0 - curGear.rotateDir;
                }
            }
        }

        // 현재 톱니 오른쪽 탐색
        for (int i = order + 1; i <= 4; i++) {
            Gear afterGear = gears[i]; // 오른쪽 톱니
            Gear curGear = gears[i - 1]; // 현재 톱니

            if (curGear.isRotate) {
                // 톱니 바퀴 극이 다르면 (n, s)
                if (afterGear.wheels[6] != curGear.wheels[2]) {
                    afterGear.isRotate = true;
                    afterGear.rotateDir = 0 - curGear.rotateDir;
                }
            }
        }
    }

    public static void rotateGears() {
        for (int i=1; i < gears.length; i++) {
            if (gears[i].isRotate) {
                int rotateDir = gears[i].rotateDir;
                int[] wheels = gears[i].wheels;
                if (rotateDir==1) {
                   int temp = wheels[7];
                   for (int j=6; j>=0; j--) {
                       wheels[j+1] = wheels[j];
                   }
                   wheels[0] = temp;
                }else {
                    int temp = wheels[0];
                    for (int j=0; j<7; j++) {
                        wheels[j] = wheels[j+1];
                    }
                    wheels[7] = temp;
                }
                gears[i].wheels = wheels;
                gears[i].isRotate = false;
                gears[i].rotateDir = 0;
            }
        }
    }
}
