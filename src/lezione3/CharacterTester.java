package lezione3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class CharacterTester {
    public static void printWithDelay(String text) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printWithDelay("You step into the chamber beyond the secret passage. Before you stands the Guardian of Eldoria’s Forgotten Oath.");
        printWithDelay("\"You have proven your intellect, but no hero walks alone,\" the Guardian intones.\n");
        printWithDelay("Three sigils glow before you. Inscribe their names and essence, and they shall be summoned to your side through this magic Reflection.");

        try {
            // Load the RPGCharacter class dynamically
            Class<?> clazz = Class.forName("lezione3.RPGCharacter");
            Constructor<?> constructor = clazz.getConstructor(String.class, int.class, String.class);
            Method describeMethod = clazz.getMethod("describe");

            // Test cases
            Object[] characters = {
                    constructor.newInstance("Eldrin", 10, "Mage"),
                    constructor.newInstance("Thalor", 15, "Warrior"),
                    constructor.newInstance("Seraphina", 20, "Paladin")
            };

            String[] expectedOutputs = {
                    "The mighty Mage known as Eldrin stands at level 10.",
                    "The mighty Warrior known as Thalor stands at level 15.",
                    "The mighty Paladin known as Seraphina stands at level 20."
            };

            // Run tests
            for (int i = 0; i < characters.length; i++) {
                String result = (String) describeMethod.invoke(characters[i]);
                boolean passed = result.equals(expectedOutputs[i]);
                printWithDelay("Test " + (i + 1) + ": " + (passed ? "PASSED" : "FAILED"));
                printWithDelay("Expected: " + expectedOutputs[i]);
                printWithDelay("Got: " + result);
            }
        } catch (ClassNotFoundException e) {
            printWithDelay("Error: RPGCharacter class not found. The Guardian shakes its head.");
            printWithDelay("Follow these steps to summon your allies:");
            printWithDelay("1) Create a class named RPGCharacter inside the lezione3 package.");
            printWithDelay("2) Ensure the class has a constructor that accepts (String, int, String).");
            printWithDelay("3) Implement a describe() method that returns a formatted string.");
            printWithDelay("4) Run this tester again once the implementation is complete.");
            System.exit(1);
        } catch (Exception e) {
            printWithDelay("An error occurred during testing:");
            e.printStackTrace();
        }
    }
}