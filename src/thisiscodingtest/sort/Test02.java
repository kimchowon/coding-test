package thisiscodingtest.sort;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 실전2. 성적이 낮은 순서로 학생 출력하기
 * 제한시간: 20분 / 소요시간: 13분
 */
public class Test02 {
    static class Student implements Comparable<Student> {
        String name;
        int grade;

        public Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }

        @Override
        public int compareTo(Student o) {
            return o.grade - this.grade;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Student> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        for (int i=0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int grade = Integer.parseInt(st.nextToken());
            pq.add(new Student(name, grade));
        }

        while (!pq.isEmpty()) {
            bw.write(pq.poll().name + " ");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
