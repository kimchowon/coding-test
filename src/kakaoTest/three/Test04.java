package kakaoTest.three;

import java.util.HashMap;
import java.util.Map;

public class Test04 {
    public static void main(String[] args) {
        String[] words =// {"go", "gone", "guild"};
                //{"word", "war", "warrior", "world"};
                {"abc", "def", "ghi", "jklm"};
        int answer = solution(words);
        System.out.println(answer);
    }

    static class TrieNode {
        Map<Character, TrieNode> childNodes = new HashMap<>();
        int cnt;
    }

    static class Trie {
        TrieNode rootNode;

        Trie() {
            rootNode = new TrieNode();
        }

        void insert(String word) {
            TrieNode thisNode = this.rootNode;

            for (int i = 0; i < word.length(); i++) {
                thisNode = thisNode.childNodes.computeIfAbsent(word.charAt(i), c -> new TrieNode());
                thisNode.cnt++;
            }
        }

        int getInputCnt(String word) {
            int count = 0;
            TrieNode thisNode = this.rootNode;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                thisNode = thisNode.childNodes.get(c);
                count++;
                if (thisNode.cnt == 1) {
                    return count;
                }
            }
            return count;
        }
    }

    public static int solution(String[] words) {
        int answer = 0;

        // Trie 생성
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        for (String word : words) {
            answer += trie.getInputCnt(word);
        }
        return answer;
    }
}
