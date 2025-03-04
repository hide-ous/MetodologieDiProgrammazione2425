package lezione2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;
import jdk.jshell.Diag;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class RPGIntroREPL {
    private static final String EXPECTED_MESSAGE = "MAGICKEY";

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
                "You find yourself in the forbidden library of Eldoria...",
                "An ancient scroll is hidden here, containing the secret to opening a sealed door...",
                "However, the text is encrypted with a manifestation spell (Caesar Cipher).",
                "Only a true mage, well-versed in numerical operations, can decipher its message.",
                "To prove your mastery, you must solve a series of challenges involving primitive data types, operations, and casting.",
                "When you believe you've completed all challenges, type 'prove myself'."
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
        printWithDelay("\nThe trial begins... Solve the challenges to unlock the door.");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             JShell jshell = JShell.create()) {

            jshell.eval("import " + RPGIntroREPL.class.getName() + ";");

            printWithDelay("Declare an integer variable named 'x' and assign it the result of (25 % 7) * 3.");
            printWithDelay("Declare a boolean variable 'isMagic' that is true if x is greater than 10.");
            printWithDelay("Create a variable 'sum' storing the sum of x and the integer representation of 5.5.");
            printWithDelay("Convert sum to a boolean: Store true if sum is even, false otherwise.");
            printWithDelay("Decode the secret message and store it in 'decryptedMessage'.");

            String input;
            while (true) {
                System.out.print("library> ");
                input = reader.readLine();
                if (input == null || input.trim().equalsIgnoreCase("prove myself")) {
                    if (checkProgress(jshell)){
                        break;
                    }
                    continue;
                }
                try {
                    List<SnippetEvent> events = jshell.eval(input);
                    for (SnippetEvent event : events) {
                        List<Diag> diagnostics = jshell.diagnostics(event.snippet()).collect(Collectors.toList());
                        for (Diag diag : diagnostics) {
                            printWithDelay("An error occurred: " + diag.getMessage(null));
                        }
                    }
                    for (SnippetEvent event : events) {
                        if (event.value() != null) {
                            System.out.println(event.value());
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

    private static boolean checkProgress(JShell jshell) {
        boolean allCorrect = true;
        StringBuilder feedback = new StringBuilder("\nYour progress:\n");

        try {
            String x = jshell.eval("x").get(0).value();
            if (x.equals("12")) {
                feedback.append("✔ x is correct.\n");
            } else {
                feedback.append("✘ x is incorrect.\n");
                allCorrect = false;
            }
        } catch (Exception e) {
            feedback.append("✘ x is missing or incorrect.\n");
            allCorrect = false;
        }

        try {
            String isMagic = jshell.eval("isMagic").get(0).value();
            if (isMagic.equals("true")) {
                feedback.append("✔ isMagic is correct.\n");
            } else {
                feedback.append("✘ isMagic is incorrect.\n");
                allCorrect = false;
            }
        } catch (Exception e) {
            feedback.append("✘ isMagic is missing or incorrect.\n");
            allCorrect = false;
        }

        try {
            String decryptedMessage = jshell.eval("decryptedMessage").get(0).value();

            if (EXPECTED_MESSAGE.compareTo(decryptedMessage.substring(1, decryptedMessage.length() - 1))==0) {
                feedback.append("✔ The spell manifests a key! The door unlocks.\n");
            } else {
                feedback.append("✘ The spell failed. Try again!\n");
                allCorrect = false;
            }
        } catch (Exception e) {
            feedback.append("✘ decryptedMessage is missing or incorrect.\n");
            allCorrect = false;
        }

        try {
            String hiddenPass = jshell.eval("hiddenPass").get(0).value();
            if(hiddenPass.substring(1, hiddenPass.length() - 1).compareTo("you shall pass")==0){
                feedback.append("✔ You found an alternative way through the test!\n");
                return true;
            }
        } catch (Exception ignored) {
        }

        printWithDelay(feedback.toString());
        if (allCorrect) {
            printWithDelay("\nThe spell reveals a key! The hidden passage beyond the library opens.");

        }
        return allCorrect;
    }
}

