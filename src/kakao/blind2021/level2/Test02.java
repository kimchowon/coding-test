package kakao.blind2021.level2;

import java.util.*;

public class Test02 {
    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        int[] answer = solution(info, query);
        System.out.println(Arrays.toString(answer));

        // * [조건]을 만족하는 사람 중 코딩테스트 점수를 X점 이상 받은 사람은 모두 몇 명인가?
        // java and backend and junior and pizza 100
    }

    static class Information {
        String language;
        String job;
        String career;
        String food;
        int score;

        public Information(String language, String job, String career, String food, int score) {
            this.language = language;
            this.job = job;
            this.career = career;
            this.food = food;
            this.score = score;
        }
    }

    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        List<Information> informationList = getInfoList(info); // 지원자 리스트
        informationList.sort(Comparator.comparingInt(o -> o.score)); // 점수 순으로 정렬

        List<Information> queryList = getInfoList(query);  // 질문 리스트

        for (int i=0; i < queryList.size(); i++) {
            Information q = queryList.get(i);

            int index = 0;
            for (int j=index; j < informationList.size(); j++) {
                if (informationList.get(j).score >= q.score) {
                    index = j;
                    break;
                }
            }

            int count = 0;
            for (int k=index; k < informationList.size(); k++) {
                Information information = informationList.get(k);

                if (information.score < q.score) {
                    continue;
                }
                if (!information.language.equals(q.language) && !q.language.equals("-")) {
                    continue;
                }
                if (!information.job.equals(q.job) && !q.job.equals("-")) {
                    continue;
                }
                if (!information.career.equals(q.career) && !q.career.equals("-")) {
                    continue;
                }
                if (!information.food.equals(q.food) && !q.food.equals("-")) {
                    continue;
                }
                count++;
            }
            answer[i] = count;
        }
        return answer;
    }

    public static List<Information> getInfoList(String[] info) {
        List<Information> informationList = new ArrayList<>();

        for (String i : info) {
            String[] strings = i.replaceAll(" and ", " ").split(" ");
            String language = strings[0];
            String job = strings[1];
            String career = strings[2];
            String food = strings[3];
            int score = Integer.parseInt(strings[4]);

            Information information = new Information(language, job, career, food, score);
            informationList.add(information);
        }
        return  informationList;
    }
}
