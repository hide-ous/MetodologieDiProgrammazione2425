package lezione13.eliza;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ScriptManager {
    private List<Rule> rules = new ArrayList<>();
    private Map<String, List<String>> defaultResponses = new HashMap<>();
    private KeywordDictionary keywordDictionary;

    public ScriptManager(KeywordDictionary dictionary) {
        this.keywordDictionary = dictionary;
        loadScripts();
    }

    public void addRule(String pattern, List<String> responses) {
        rules.add(new Rule(pattern, responses));
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void addDefaultResponse(String key, List<String> responses) {
        defaultResponses.put(key, responses);
    }

    public String getRandomDefaultResponse(String key) {
        if (defaultResponses.containsKey(key) && !defaultResponses.get(key).isEmpty()) {
            Random random = new Random();
            return defaultResponses.get(key).get(random.nextInt(defaultResponses.get(key).size()));
        }
        return null;
    }

    private void loadScripts() {
        addRule("i need (.*)", List.of("Why do you feel you need $1?", "What would having $1 do for you?"));
        addRule("i am (.*)", List.of("How long have you been feeling $1?", "Do you believe it is good to be $1?"));
        addRule("i believe (.*)", List.of("Do you really think $1?", "What makes you believe $1?"));
        addRule("(.*) reminds me of (.*)", List.of("What else does $1 remind you of?", "Are there any similarities between $1 and $2?"));
        // Modified rule to include "about" (Option 1)
        addRule("i dreamed about (.*)", List.of("What feelings did you have in the dream about $1?", "What does that dream about $1 suggest to you?"));
        addRule("my mother (.*)", List.of("Tell me more about your family.", "What kind of relationship do you have with your mother?"));
        addRule("i am sorry", List.of("Please don't apologize.", "What are you sorry about?"));
        addRule("because (.*)", List.of("Is that the only reason?", "What else could be contributing to $1?"));
        addRule("(hello|hi)", List.of("Hello. How can I help you today?", "Hi there. Please tell me what's on your mind."));

        addDefaultResponse("general", List.of("Tell me more about that.", "That's interesting.", "Can you elaborate on that?", "How does that make you feel?"));
        addDefaultResponse("goodbye", List.of("Goodbye.", "It was nice talking to you.", "Take care."));
    }

    static class Rule {
        public String pattern;
        private List<String> responses;
        private Pattern regexPattern;

        public Rule(String pattern, List<String> responses) {
            this.pattern = pattern;
            this.responses = responses;
            this.regexPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        }

        public Pattern regexPattern() {
            return regexPattern;
        }

        public Matcher match(String input) {
            return regexPattern.matcher(input);
        }

        public String getRandomResponse() {
            if (!responses.isEmpty()) {
                Random random = new Random();
                return responses.get(random.nextInt(responses.size()));
            }
            return null;
        }
    }
}
