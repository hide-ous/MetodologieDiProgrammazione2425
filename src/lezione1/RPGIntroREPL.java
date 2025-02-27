package lezione1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;
import jdk.jshell.VarSnippet;
import jdk.jshell.Diag;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class RPGIntroREPL {
    private static String name = "";
    private static Integer age = null;
    private static String role = "";

    public static void main(String[] args) {
        showIntro();
        launchREPL();
    }

    private static void printWithDelay(String text) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            sleep(20);
        }
        System.out.println();
        sleep(200);
    }

    private static void showIntro() {
        String[] story = {
                "In the ancient lands of Eldoria, where magic and steel intertwine...",
                "The kingdoms stand on the brink of war, and darkness looms at the edge of the realm...",
                "Legends speak of a hero who will shape the fate of all...",
                "But first, the hero must discover who they truly are..."
        };

        for (String line : story) {
            printWithDelay(line);
        }
        printWithDelay("\nWelcome, adventurer. Your journey begins with self-discovery.");
        printWithDelay("Describe yourself to the ancient scribe who records destinies.");
        printWithDelay("Tell them your name, your years of experience, and the path you walk in life.");
        printWithDelay("The scribe is a little hard of hearing and only understands correct syntax.");
        printWithDelay("When you feel ready, test yourself by saying 'prove myself'.");
    }

    private static void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void launchREPL() {
        printWithDelay("\nThe scribe listens... Speak wisely.");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             JShell jshell = JShell.create()) {

            jshell.eval("import " + RPGIntroREPL.class.getName() + ";");

            String input;
            while (true) {
                System.out.print("scribe> ");
                input = reader.readLine();
                if (input == null || input.trim().equalsIgnoreCase("prove myself")) {
                    if (name.isEmpty() || age == null || role.isEmpty()) {
                        StringBuilder missing = new StringBuilder("The scribe frowns. 'You have not yet completed your story. Missing: ");
                        if (name.isEmpty()) missing.append("name, ");
                        if (age == null) missing.append("age, ");
                        if (role.isEmpty()) missing.append("role, ");
                        printWithDelay(missing.substring(0, missing.length() - 2) + "'.");
                        continue;
                    }
                    printWithDelay("\nThe scribe nods solemnly and inscribes the final words...");
                    printWithDelay("----------------------");
                    printWithDelay("Name: " + name);
                    printWithDelay("Age: " + age);
                    printWithDelay("Path: " + role);
                    printWithDelay("----------------------");
                    printWithDelay("Go forth, " + name + ", the " + age + "-year-old " + role + ", and fulfill your destiny!");
                    break;
                }
                try {
                    List<SnippetEvent> events = jshell.eval(input);
                    for (SnippetEvent event : events) {
                        List<Diag> diagnostics = jshell.diagnostics(event.snippet()).collect(Collectors.toList());
                        for (Diag diag : diagnostics) {
                            printWithDelay("The scribe looks puzzled. An error has occurred: " + diag.getMessage(null));
                        }
                    }
                    for (SnippetEvent event : events) {
                        if (event.value() != null) {
                            System.out.println(event.value());
                        }
                        if (event.snippet() instanceof VarSnippet) {
                            String varName = ((VarSnippet) event.snippet()).name();
                            if (varName.equals("name")) {
                                name = jshell.eval("name").get(0).value();
                                printWithDelay("The scribe records: You shall be known as " + name + ".");
                            } else if (varName.equals("age")) {
                                age = Integer.parseInt(jshell.eval("age").get(0).value());
                                printWithDelay("The scribe notes: You have walked this world for " + age + " years.");
                            } else if (varName.equals("role")) {
                                role = jshell.eval("role").get(0).value();
                                printWithDelay("The scribe acknowledges: You have chosen the path of the " + role + ".");
                            }
                        }
                    }
                } catch (Exception e) {
                    printWithDelay("The scribe looks puzzled. An unexpected error has occurred: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }
        } catch (IOException e) {
            printWithDelay("The scribe is troubled: An unexpected error has occurred: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }
}

