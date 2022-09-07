package utils;

import java.util.Scanner;

public class InputReader {
    private static boolean startsWithSpace(String input) {
        if (input.startsWith(" ")) {
            System.out.println("Your input starts with a space. This is not allowed. Try again.");

            return true;
        }

        return false;
    }

    public static String requestTextInput(String request) {
        System.out.println();
        System.out.print(request);

        String input = readUserInput();

        if (startsWithSpace(input)) {
            input = requestTextInput(request);
        }

        return input;
    }

    private static String readUserInput() {
        Scanner keyboard = new Scanner(System.in);

        return keyboard.nextLine();
    }
}
