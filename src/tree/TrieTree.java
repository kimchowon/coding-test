package tree;

import java.util.HashMap;
import java.util.Map;

public class TrieTree {

    static class TrieNode {
        private Map<Character, TrieNode> childNodes = new HashMap<>();
        private boolean isLastChar;
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
            }
            thisNode.isLastChar = true;
        }

        boolean contains(String word) {
            TrieNode thisNode = this.rootNode;

            for (int i = 0; i < word.length(); i++) {
                char character = word.charAt(i);

                TrieNode node = thisNode.childNodes.get(character);

                if (node == null) {
                    return false;
                }

                thisNode = node;
            }
            return thisNode.isLastChar;
        }

        void delete(String word) {
            delete(this.rootNode, word, 0);
        }

        void delete(TrieNode thisNode, String word, int index) {
            char character = word.charAt(index);

            if (!thisNode.childNodes.containsKey(character)) {
                throw new Error("There is no [" + word + "] in this Trie.");
            }

            TrieNode childNode = thisNode.childNodes.get(character);
            index++;

            if (index == word.length()) {
                if (!childNode.isLastChar) {
                    throw new Error("There is no [" + word + "] in this Trie.");
                }
                childNode.isLastChar = false;

                if (childNode.childNodes.isEmpty()) {
                    thisNode.childNodes.remove(character);
                }
            } else {
                delete(childNode, word, index);

                if (!childNode.isLastChar && childNode.childNodes.isEmpty()) {
                    thisNode.childNodes.remove(character);
                }
            }
        }
    }
}
