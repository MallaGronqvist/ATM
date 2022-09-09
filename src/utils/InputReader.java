package utils;

import menus.mainMenu.MainMenu;

import java.util.Scanner;

import static utils.InputValidator.*;

public class InputReader {

    public static String requestInput(String request, String inputType) {
        System.out.println();
        System.out.println(request);

        displayInstructions(inputType);

        String input = readUserInput();

        if (blancInput(input) || (startsWithWhitespace(input)) ||
                (inputType.equals("name") && (!validLetters(input))) ||
                (inputType.equals("name")  && startsWithInvalidCharacter(input)) ||
                (inputType.equals("credential") && containsWhiteSpace(input)) ||
                (inputType.equals("credential") && (!validCredentialLength(input))) ||
                (inputType.equals("amount") && startsWithZero(input))){

            System.out.println("Try again.");
            input = requestInput(request, inputType);
        }

        return input;
    }

    private static void displayInstructions(String inputType) {
        switch(inputType){
            case "name" -> System.out.println("Allowed characters: \na-z, A-Z, " +
                    "and separating apostrophe ('), hyphen (-) and whitespace.");
            case "credential" -> System.out.println("Required length 6 - 20 characters. White space not allowed.");
            case "accountNumber" -> System.out.println("Digits and hyphen allowed.");
            case "amount" -> System.out.println("\nUse comma (,) or dot (.) for decimal numbers.");
            default -> {}
        }
        System.out.println();
        System.out.println("Enter 'x' to discontinue: ");
    }

    private static String readUserInput() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }

    public static boolean getConfirmation() {
        System.out.println();
        System.out.println("Type 'Y' for yes or 'N' for no:");
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

    public static void checkForDiscontinuedInput(String input){
        if(input.equalsIgnoreCase("x")){
            new MainMenu();
        }
    }
}
