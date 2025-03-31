package lezione13;

import java.util.*;
import java.util.regex.*;

class Rule {
    private String pattern;
    private List<String> responses;
    private Pattern regexPattern;

    public Rule(String pattern, List<String> responses) {
        this.pattern = pattern;
        this.responses = responses;
        this.regexPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    }

    public Matcher match(String input) {
        return regexPattern.matcher(input);
    }

    public String getRandomResponse(Random random) {
        return responses.get(random.nextInt(responses.size()));
    }
}

class InputHandler {
    private PatternMatcher patternMatcher;
    private static final Map<String, String> CONTRACTIONS = Map.of(
            "i'm", "i am",
            "you're", "you are",
            "i've", "i have",
            "don't", "do not"
    );

    public InputHandler(PatternMatcher matcher) {
        this.patternMatcher = matcher;
    }

    public String processInput(String input) {
        String normalizedInput = normalizeInput(input);
        return patternMatcher.match(normalizedInput);
    }

    private String normalizeInput(String text) {
        text = text.toLowerCase().replaceAll("[^a-zA-Z\s]", "");
        for (Map.Entry<String, String> entry : CONTRACTIONS.entrySet()) {
            text = text.replace(entry.getKey(), entry.getValue());
        }
        return text.trim();
    }
}

class PatternMatcher {
    private List<Rule> rules;
    private Random random;

    public PatternMatcher(List<Rule> rules) {
        this.rules = rules;
        this.random = new Random();
    }

    public String match(String input) {
        for (Rule rule : rules) {
            Matcher matcher = rule.match(input);
            if (matcher.find()) {
                String response = rule.getRandomResponse(random);
                // Only replace placeholders if there are matching groups
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    response = response.replace("$" + i, matcher.group(i));
                }
                return response;
            }
        }
        return "I see. Tell me more.";
    }
}

public class ElizaSystem {
    public static void main(String[] args) {
        List<Rule> rules = new ArrayList<>();
        rules.add(new Rule("i need (.*)", List.of("Why do you feel you need $1?", "What would having $1 do for you?")));
        rules.add(new Rule("i am (.*)", List.of("How long have you been feeling $1?", "Do you believe it is good to be $1?")));
        rules.add(new Rule("hello|hi", List.of("Hello! How can I help you today?", "Hi there! What's on your mind?")));

        PatternMatcher matcher = new PatternMatcher(rules);
        InputHandler handler = new InputHandler(matcher);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello. How can I help you today?");

        while (true) {
            System.out.print("> ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye!");
                break;
            }
            System.out.println(handler.processInput(userInput));
        }
        scanner.close();
    }
}
