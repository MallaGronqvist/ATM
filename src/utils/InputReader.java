package utils;

import menus.mainMenu.MainMenu;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputReader {

    private static boolean startsWithInvalidCharacter(String input){
        if (input.equals("")) {
            System.out.println("Blanc input not allowed. Try again.");
            return true;
        }else if (input.startsWith(" ") || input.startsWith("'") || input.startsWith("0") || input.startsWith("-")) {
            System.out.println("Leading whitespace, hyphen, apostrophe or zero not allowed. Try again.");
            return true;
        }
        return false;
    }

    public static String requestInput(String request, String inputType) {
        System.out.println();
        System.out.println(request);

        switch(inputType){
            case "name" -> System.out.println("Allowed characters: \na-z, A-Z, apostrophe ('), hyphen (-) " +
                    "and separating whitespace.");
            case "credential" -> System.out.println("Leading whitespace, hyphen, apostrophe or zero not allowed." +
                    " White space not allowed.");
            case "accountNumber" -> System.out.println("Digits and hyphen allowed.");
            case "amount" -> System.out.println("\nUse comma (,) or dot (.) for decimal numbers.");
            default -> {}
        }
        System.out.println();
        System.out.println("Enter 'x' to discontinue: ");

        String input = readUserInput();

        if (startsWithInvalidCharacter(input)) {
            input = requestInput(request, inputType);
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

    public static void checkForDiscontinuedInput(String input){
        if(input.equalsIgnoreCase("x")){
            new MainMenu();
        }
    }

    public static boolean validateLetters(String input) {
        // Validate input to contain only letters a-z, whitespace, apostrophe and hyphen.
        String allowedCharacters = "^[a-zA-Z\\s'-]+$";
        Pattern pattern = Pattern.compile(allowedCharacters, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
