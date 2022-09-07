package utils;

import menus.mainMenu.MainMenu;

import java.util.Scanner;

public class InputReader {
    private static boolean startsWithSpace(String input) {
        if (input.startsWith(" ")) {
            System.out.println("Your input starts with a space. This is not allowed. Try again.");

            return true;
        }
        return false;
    }

    private static boolean startsWithZero(String input) {
        if (input.startsWith("0")) {
            System.out.println("Your input starts with a zero. This is not allowed. Try again.");

            return true;
        }
        return false;
    }

    private static boolean containsNoCharacters(String input){
        if (input.equals("")) {
            System.out.println("No input. This is not allowed. Try again.");

            return true;
        }
        return false;
    }

    public static String requestTextInput(String request) {
        System.out.println();
        System.out.println(request);
        System.out.println("[ Enter 'x' to discontinue ]: ");

        String input = readUserInput();

        if (startsWithSpace(input) || startsWithZero(input) || containsNoCharacters(input)) {
            input = requestTextInput(request);
        }
        return input;
    }

    private static String readUserInput() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }

    public static boolean getConfirmation() {
        System.out.println("Type 'Y' for yes or 'N' for no.");
        String answer = readUserInput();

        if (answer.equalsIgnoreCase("y")) {
            return true;
        } else if (answer.equalsIgnoreCase("n")) {
            return false;
        } else {
            System.out.println("Invalid input. Try again");
            return getConfirmation();
        }
    }

    public static void checkForDiscontinuedOperation(String input){
        if(input.equalsIgnoreCase("x")){
            new MainMenu();
        }
    }
}
