package utility;

import java.util.Scanner;

public class MusicUtility {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        checkForExit(input);
        return input;
    }

    public static int readInt(String prompt) {
        while (true) {
            String input = readString(prompt);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            String input = readString(prompt);
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid decimal number.");
            }
        }
    }

    public static void checkForExit(String input) {
        if (input.equalsIgnoreCase("EXIT")) {
            System.out.println("Exiting program...");
            scanner.close();
            System.exit(0);
        }
    }

}
