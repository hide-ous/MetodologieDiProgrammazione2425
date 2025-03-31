package lezione13.eliza;

import java.util.Scanner;


public class ElizaSystem {
    private InputHandler inputHandler;
    private ResponseGenerator responseGenerator;
    private PatternMatcher patternMatcher;
    private ScriptManager scriptManager;
    private ConversationMemory conversationMemory;
    private KeywordDictionary keywordDictionary;
    private boolean running = true;

    public ElizaSystem() {
        keywordDictionary = new KeywordDictionary();
        conversationMemory = new ConversationMemory();
        scriptManager = new ScriptManager(keywordDictionary);
        responseGenerator = new ResponseGenerator(scriptManager, conversationMemory);
        patternMatcher = new PatternMatcher(scriptManager, responseGenerator, keywordDictionary);
        inputHandler = new InputHandler(this, patternMatcher);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello. How can I help you today?");

        while (running) {
            System.out.print("> ");
            String userInput = scanner.nextLine();
            conversationMemory.addInput(userInput);

            if (userInput.equalsIgnoreCase("bye") || userInput.equalsIgnoreCase("goodbye")) {
                System.out.println(scriptManager.getRandomDefaultResponse("goodbye"));
                running = false;
            } else {
                String response = inputHandler.processInput(userInput);
                System.out.println(response);
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        ElizaSystem eliza = new ElizaSystem();
        eliza.start();
    }
}