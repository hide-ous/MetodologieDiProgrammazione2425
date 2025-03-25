package lezione8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.stream.Collectors;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;
import jdk.jshell.Diag;

public class GlyphCipherREPL {
    private static boolean success = false;

    public static void main(String[] args) {
        showIntro();
        launchREPL();
    }

    private static void printWithDelay(String text) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            sleep(15);  // Faster animation but still readable
        }
        System.out.println();
        sleep(150);
    }

    private static void showIntro() {
        String[] story = {
                "The hero steps into a chamber filled with glowing glyphs, shifting and swirling in the air...",
                "A voice whispers: 'The Song of the Ancients is hidden within the glyphs.'",
                "'Only one who can decipher the sacred sequence may pass.'",
                "To complete this trial, you must create a class named 'Decoder' in a separate file:",
                "",
                "    package lezione8;",
                "    public class Decoder {",
                "        public static String decode(char[] scrambled, int[] order) {",
                "            // Your code here",
                "        }",
                "    }",
                "",
                "Once compiled, run this program and type 'prove myself' to test it."
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
        printWithDelay("\nThe glyphs shift... The test begins.");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             JShell jshell = JShell.create()) {

            printWithDelay("Loading your Decoder class...");
            jshell.eval("import lezione8.Decoder;"); // Import properly

            // Check if class exists
            List<SnippetEvent> classCheck = jshell.eval("Class.forName(\"lezione8.Decoder\");");
            if (classCheck.stream().anyMatch(e -> e.exception() != null)) {
                printWithDelay("\n❌ Error: Could not find `Decoder` class in package `lezione8`.");
                printWithDelay("Make sure it is compiled correctly and in the right package.");
                printWithDelay("Try running:\n    javac -d . Decoder.java\nThen restart the REPL.");
                return;
            }

            // Verify `decode()` method exists
            List<SnippetEvent> methodCheck = jshell.eval("lezione8.Decoder.class.getMethod(\"decode\", char[].class, int[].class);");
            if (methodCheck.stream().anyMatch(e -> e.exception() != null)) {
                printWithDelay("\n❌ Error: `decode()` method is missing or has the wrong signature.");
                printWithDelay("Ensure your class contains:");
                printWithDelay("    public static String decode(char[] scrambled, int[] order)");
                return;
            }

            printWithDelay("\n✅ `Decoder` is loaded! You can now test your implementation.");

            String input;
            while (true) {
                System.out.print("glyph> ");
                input = reader.readLine();

                if (input == null || input.trim().equalsIgnoreCase("prove myself")) {
                    checkSolution(jshell);
                    continue;
                }

                try {
                    List<SnippetEvent> events = jshell.eval(input);
                    for (SnippetEvent event : events) {
                        printDiagnostics(jshell, event);
                        if (event.value() != null) {
                            printWithDelay("Output: " + event.value());
                        }
                    }
                } catch (Exception e) {
                    printWithDelay("❌ An unexpected error occurred:");
                    e.printStackTrace(System.out);
                }
            }
        } catch (IOException e) {
            printWithDelay("❌ An unexpected I/O error occurred:");
            e.printStackTrace(System.out);
        }
    }

    private static void checkSolution(JShell jshell) {
        printWithDelay("\nThe glyphs glow brighter as you attempt to reveal the hidden message...");

        // Define test cases
        String[] scrambledDefinitions = {
                "char[] scrambled1 = new char[]{'l', 'o', 'l', 'e', 'H', ' ', 'r', 'd', '!', 'o', 'w'};",
                "char[] scrambled2 = new char[]{'C', 'd', 's', 'e', 'o', '!', ' ', 'r', 't', 'y', 'p'};",
                "char[] scrambled3 = new char[]{'♩', '♪', '♫', '♬', ' ', '♩', '♪', '♫', '♬'};"
        };

        String[] orderDefinitions = {
                "int[] order1 = new int[]{4, 1, 9, 3, 5, 6, 10, 2, 8, 0, 7};",
                "int[] order2 = new int[]{9, 3, 6, 8, 5, 2, 7, 0, 10, 4, 1};",
                "int[] order3 = new int[]{4, 0, 5, 1, 6, 2, 7, 3, 8};"
        };

        String[] expectedOutputs = {
                "Hello world!",
                "Code party!",
                "♩♩ ♪♪ ♫♫ ♬♬ " // Expected melody sequence
        };

        boolean allTestsPassed = true;

        for (int i = 0; i < scrambledDefinitions.length; i++) {
            printWithDelay("\n🔍 Testing case " + (i + 1) + "...");

            // Define arrays in JShell
            jshell.eval(scrambledDefinitions[i]);
            jshell.eval(orderDefinitions[i]);

            // Run the test using pre-defined variables
            List<SnippetEvent> events = jshell.eval("lezione8.Decoder.decode(scrambled" + (i + 1) + ", order" + (i + 1) + ");");

            boolean testPassed = false;
            for (SnippetEvent event : events) {
                printDiagnostics(jshell, event);
                if (event.value() != null && event.value().equals(expectedOutputs[i])) {
                    testPassed = true;
                    break;
                }
            }

            if (testPassed) {
                printWithDelay("\n✅ Test " + (i + 1) + " passed: " + expectedOutputs[i]);
            } else {
                printWithDelay("\n❌ Test " + (i + 1) + " failed.");
                allTestsPassed = false;
            }
        }

        if (allTestsPassed) {
            printWithDelay("\n✨ Success! The glyphs form the sacred words...");
            printWithDelay("A melody plays, and the passage opens before you.");
            success = true;
        } else {
            printWithDelay("\nThe glyphs remain unreadable... Keep working.");
        }
    }

    private static void printDiagnostics(JShell jshell, SnippetEvent event) {
        List<Diag> diagnostics = jshell.diagnostics(event.snippet()).collect(Collectors.toList());
        if (!diagnostics.isEmpty()) {
            printWithDelay("\n❌ Compilation errors detected:");
            for (Diag diag : diagnostics) {
                printWithDelay(diag.getMessage(null));
            }
        }
        if (event.exception() != null) {
            printWithDelay("\n❌ Runtime error occurred:");
            event.exception().printStackTrace(System.out);
        }
    }
}
