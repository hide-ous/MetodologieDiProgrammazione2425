package lezione13.eliza;

class InputHandler {
    private ElizaSystem elizaSystem;
    private PatternMatcher patternMatcher;

    public InputHandler(ElizaSystem system, PatternMatcher matcher) {
        this.elizaSystem = system;
        this.patternMatcher = matcher;
    }

    public String processInput(String input) {
        String normalizedInput = normalizeInput(input);
//        System.out.println("Normalized Input: [" + normalizedInput + "]"); // Debugging
        return patternMatcher.match(normalizedInput);
    }

    private String normalizeInput(String text) {
        return text.replaceAll("[^a-zA-Z\\s]", "").toLowerCase().trim();
    }
}
