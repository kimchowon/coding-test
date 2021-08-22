package programmers.level2;

/**
 * 스킬 트리
 */
public class Test02 {
    public static void main(String[] args) {
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        String skill = "B";
        System.out.println("solution : " + solution(skill, skill_trees));
    }

    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (int i = 0; i < skill_trees.length; i++) {
            String skill_element = skill_trees[i].replaceAll("[^" + skill + "]", "");

            if (skill.indexOf(skill_element) == 0) {
                answer++;
            }
        }
        return answer;
    }
}
