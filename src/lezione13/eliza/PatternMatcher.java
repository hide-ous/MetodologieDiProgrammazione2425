package lezione13.eliza;

import java.util.regex.Matcher;

class PatternMatcher {
    private ScriptManager scriptManager;
    private ResponseGenerator responseGenerator;
    private KeywordDictionary keywordDictionary;

    public PatternMatcher(ScriptManager scriptManager, ResponseGenerator responseGenerator, KeywordDictionary keywordDictionary) {
        this.scriptManager = scriptManager;
        this.responseGenerator = responseGenerator;
        this.keywordDictionary = keywordDictionary;
    }

    public String match(String input) {
        for (ScriptManager.Rule rule : scriptManager.getRules()) {
//            System.out.println("Trying rule pattern: " + rule.regexPattern().pattern()); // Debugging
            Matcher matcher = rule.match(input);
            if (matcher.matches()) {
                System.out.println("Match found for: " + rule.pattern); // Debugging
                return responseGenerator.generateResponse(rule, matcher);
            }
        }
        return responseGenerator.generateDefaultResponse();
    }
}
