package lezione13.eliza;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;

class ResponseGenerator {
    private ScriptManager scriptManager;
    private ConversationMemory conversationMemory;
    private Random random = new Random();

    public ResponseGenerator(ScriptManager scriptManager, ConversationMemory memory) {
        this.scriptManager = scriptManager;
        this.conversationMemory = memory;
    }

    public String generateResponse(ScriptManager.Rule rule, Matcher matcher) {
        String rawResponse = rule.getRandomResponse();
        if (rawResponse != null) {
            if (matcher.groupCount() > 0) {
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    String capturedGroup = matcher.group(i).trim();
                    String transformedGroup = transformInput(capturedGroup);
                    rawResponse = rawResponse.replace("$" + i, capturedGroup);
                    if (rawResponse.contains("feel you need")) {
                        rawResponse = rawResponse.replace("feel you need", "think you need");
                    }
                }
            }
            conversationMemory.addResponse(rawResponse);
            return rawResponse;
        }
        return generateDefaultResponse();
    }

    public String generateDefaultResponse() {
        String response = scriptManager.getRandomDefaultResponse("general");
        if (response != null && !conversationMemory.hasRecentResponse(response)) {
            conversationMemory.addResponse(response);
            return response;
        }
        return "Let's talk more about that.";
    }

    private String transformInput(String text) {
        Map<String, String> reflections = Map.of(
                "i am", "you are",
                "i was", "you were",
                "me", "you",
                "my", "your",
                "you are", "I am",
                "you were", "I was",
                "your", "my");

        String[] words = text.split("\\s+");
        List<String> transformedWords = new ArrayList<>();
        for (String word : words) {
            transformedWords.add(reflections.getOrDefault(word, word));
        }
        return String.join(" ", transformedWords);
    }
}
