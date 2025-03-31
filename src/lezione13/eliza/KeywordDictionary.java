package lezione13.eliza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class KeywordDictionary {
    private Map<String, Integer> keywords = new HashMap<>();
    private Map<String, List<String>> keywordPatterns = new HashMap<>();
    private Map<String, List<String>> synonyms = new HashMap<>();

    public void addKeyword(String keyword, int priority) {
        keywords.put(keyword, priority);
        keywordPatterns.put(keyword, new ArrayList<>());
        synonyms.put(keyword, new ArrayList<>());
    }

    public void addPattern(String keyword, String pattern) {
        keywordPatterns.get(keyword).add(pattern);
    }

    public void addSynonym(String keyword, String synonym) {
        synonyms.get(keyword).add(synonym);
    }

    public boolean containsKeyword(String word) {
        return keywords.containsKey(word) || synonyms.values().stream().anyMatch(list -> list.contains(word));
    }

    public List<String> getPatterns(String word) {
        if (keywords.containsKey(word)) {
            return keywordPatterns.get(word);
        } else {
            for (Map.Entry<String, List<String>> entry : synonyms.entrySet()) {
                if (entry.getValue().contains(word)) {
                    return keywordPatterns.get(entry.getKey());
                }
            }
        }
        return new ArrayList<>();
    }
}

