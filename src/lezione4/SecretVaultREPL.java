package lezione4;

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

public class SecretVaultREPL {
    private static boolean success = false;
    private static String HERO = "Blancmange the White";

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
                "The hero, "+HERO+", steps into a hidden chamber, discovering a glowing inscription listing the names of legendary warriors...",
                "Yet, their own name is missing. To prove their worth, they must find a way to add themselves to the list.",
                "A strange magical terminal hums before them. 'The Guardian records only those worthy,' it whispers.",
                "However, the system seems flawed. The list of participants is supposedly sealed, but something about its structure feels... vulnerable.",
                "Use the console to interact with the vault and uncover its secrets.",
                "When you believe you have succeeded, type 'prove myself'."
        };

        for (String line : story) {
            printWithDelay(line);
        }
    }

    private static void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void launchREPL() {
        printWithDelay("\nThe trial begins... Find a way to inscribe your name.");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             JShell jshell = JShell.create()) {

            jshell.eval("import lezione4.SecretVault;");
            jshell.eval("import lezione4.SecretVault.VaultHelper;");
            jshell.eval("import java.util.List;");
            jshell.eval("SecretVault vault = new SecretVault();");

            // Print initial list
            List<SnippetEvent> initialEvents = jshell.eval("vault.getHelper().getParticipants().toString()");
            for (SnippetEvent event : initialEvents) {
                if (event.value() != null) {
                    printWithDelay("Initial list of participants: " + event.value());
                }
            }

            String input;
            while (true) {
                System.out.print("vault> ");
                input = reader.readLine();
                if (input == null || input.trim().equalsIgnoreCase("prove myself")) {
                    checkManipulation(jshell);
                    continue;
                }
                try {
                    List<SnippetEvent> events = jshell.eval(input);
                    for (SnippetEvent event : events) {
                        List<Diag> diagnostics = jshell.diagnostics(event.snippet()).collect(Collectors.toList());
                        for (Diag diag : diagnostics) {
                            printWithDelay("An error occurred: " + diag.getMessage(null));
                        }
                        if (event.value() != null) {
                            printWithDelay("Output: " + event.value());
                        }
                    }
                } catch (Exception e) {
                    printWithDelay("An unexpected error has occurred: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }
        } catch (IOException e) {
            printWithDelay("An unexpected error has occurred: " + e.getMessage());
            e.printStackTrace(System.out);
        }
    }

    private static void checkManipulation(JShell jshell) {
        try {
            List<SnippetEvent> events = jshell.eval("vault.getHelper().getParticipants().toString()");
            for (SnippetEvent event : events) {
                if (event.value() != null) {
                    printWithDelay("\nCurrent list of participants: " + event.value());
                    if (event.value().contains(HERO)) {
                        printWithDelay("\nSuccess! Your name now stands among the legends of Eldoria.");
                        success = true;
                        break;
                    }
                }
            }
            if (!success) {
                printWithDelay("\nYour name remains absent. Keep searching for a way to inscribe it.");
            } else {
                System.exit(0);
            }
        } catch (Exception e) {
            printWithDelay("An unexpected error occurred while checking for manipulation.");
            e.printStackTrace(System.out);
        }
    }
}