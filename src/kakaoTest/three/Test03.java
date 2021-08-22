package kakaoTest.three;

import java.util.HashMap;
import java.util.Map;

/**
 * 자동 완성
 * 소요시간: 57분 38초
 */
public class Test03 {
    public static void main(String[] args) {
        String[] words =// {"go", "gone", "guild"};
                //{"word", "war", "warrior", "world"};
                {"abc", "def", "ghi", "jklm"};
        int answer = solution(words);
        System.out.println(answer);
    }

    static class TrieNode {
        private Map<Character, TrieNode> childNodes = new HashMap<>();
        private int cnt;
    }

    static class Trie {
        TrieNode rootNode;

        public Trie() {
            rootNode = new TrieNode();
        }

        void insert(String word) {
            TrieNode thisNode = this.rootNode;

            for (int i = 0; i < word.length(); i++) {
                thisNode = thisNode.childNodes.computeIfAbsent(word.charAt(i), c -> new TrieNode());
                thisNode.cnt++;
            }
        }

        int getInputCounts(String word) {
            int count = 0;
            TrieNode thisNode = this.rootNode;

            for (int i = 0; i < word.length(); i++) {
                char character = word.charAt(i);
                TrieNode node = thisNode.childNodes.get(character);
                count++;

                if (node.cnt == 1) {
                    return count;
                }
                thisNode = node;
            }
            return count;
        }
    }

    public static int solution(String[] words) {
        int answer = 0;

        // Trie 트리 생성
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        for (String word : words) {
            answer += trie.getInputCounts(word);
        }
        return answer;
    }
}

