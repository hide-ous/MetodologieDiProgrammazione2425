package lezione1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;
import jdk.jshell.VarSnippet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RPGIntroREPL {
    private static String name = "";
    private static Integer age = null;
    private static String role = "";

    public static void main(String[] args) {
        showIntro();
        launchREPL();
    }

    private static void showIntro() {
        String[] story = {
                "In the ancient lands of Eldoria, where magic and steel intertwine...",
                "The kingdoms stand on the brink of war, and darkness looms at the edge of the realm...",
                "Legends speak of a hero who will shape the fate of all...",
                "But first, the hero must discover who they truly are..."
        };

        for (String line : story) {
            for (char c : line.toCharArray()) {
                System.out.print(c);
                sleep(50);
            }
            System.out.println();
            sleep(500);
        }
        System.out.println("\nWelcome, adventurer. Let's begin your journey of self-discovery.");
        System.out.println("To progress, define your character using Java expressions.");
        System.out.println("Examples:");
        System.out.println("- name = \"Eldrin\"; // Choose your name");
        System.out.println("- age = 25; // Set your age");
        System.out.println("- role = \"Mage\"; // Define your role");
        System.out.println("Modify these attributes to advance the narrative.");
    }

    private static void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void launchREPL() {
        System.out.println("\nEntering the Java REPL. Define yourself, or type 'exit' to leave.");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             JShell jshell = JShell.create()) {

            jshell.eval("import " + RPGIntroREPL.class.getName() + ";");

            String input;
            while (true) {
                System.out.print("jshell> ");
                input = reader.readLine();
                if (input == null || input.trim().equalsIgnoreCase("exit")) {
                    if (name.isEmpty() || age == null || role.isEmpty()) {
                        System.out.println("You must define all attributes (name, age, role) before concluding.");
                        continue;
                    }
                    System.out.println("\nFinal Character Sheet:");
                    System.out.println("----------------------");
                    System.out.println("Name: " + name);
                    System.out.println("Age: " + age);
                    System.out.println("Role: " + role);
                    System.out.println("----------------------");
                    System.out.println("Your journey begins, " + name + ", the " + age + "-year-old " + role + "!");
                    System.out.println("Farewell, and may your choices shape the world of Eldoria!");
                    break;
                }
                try {
                    List<SnippetEvent> events = jshell.eval(input);
                    for (SnippetEvent event : events) {
                        if (event.value() != null) {
                            System.out.println(event.value());
                        }
                        if (event.snippet() instanceof VarSnippet) {
                            String varName = ((VarSnippet) event.snippet()).name();
                            if (varName.equals("name")) {
                                name = jshell.eval("name").get(0).value();
                                System.out.println("You are now known as " + name + ".");
                            } else if (varName.equals("age")) {
                                age = Integer.parseInt(jshell.eval("age").get(0).value());
                                System.out.println("You are " + age + " years old.");
                            } else if (varName.equals("role")) {
                                role = jshell.eval("role").get(0).value();
                                System.out.println("You have chosen the path of the " + role + ".");
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }
        } catch (IOException e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }
}

