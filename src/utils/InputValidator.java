package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    // Firstnames and lastnames can contain, but cannot begin with hyphen or dash.
    static boolean startsWithInvalidCharacter(String input){

        if (input.startsWith("'") || input.startsWith("-")) {
            MenuPrinter.clearConsole();
            System.out.println("Invalid input with leading hyphen or apostrophe.");
            return true;
        } else{
            return false;
        }
    }

    static boolean startsWithWhitespace(String input){

        if(input.startsWith(" ")){
            MenuPrinter.clearConsole();
            System.out.println("Invalid input with leading whitespace.");
            return true;
        } else {
            return false;
        }
    }

    static boolean blancInput(String input){
        if (input.equals("")) {
            MenuPrinter.clearConsole();
            System.out.println("Blanc input not allowed.");
            return true;
        } else {
            return false;
        }
    }
    // Usernames and passwords are not allowed to contain whitespace to minimize the risk
    // of confusion around credentials.
    static boolean containsWhiteSpace(String input){
        if(input.contains(" ")){
            MenuPrinter.clearConsole();
            System.out.println("Invalid input with whitespace.");
            return true;
        } else {
            return false;
        }
    }

    // An amount is not allowed to begin with a leading 0.
    public static boolean startsWithZero(String input) {
        if(input.startsWith("0")) {
            MenuPrinter.clearConsole();
            System.out.println("Leading zeros are not allowed.");
            return true;
        } else {
            return false;
        }
    }

    // Due to problems that can occur with the Windows command prompt displaying
    // special Unicode characters, only letters a-z are allowed.
    public static boolean validLetters(String input) {
        // Validate input to contain only letters a-z, whitespace, apostrophe and hyphen.
        String allowedCharacters = "^[a-zA-Z\\s'-]+$";
        Pattern pattern = Pattern.compile(allowedCharacters, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        MenuPrinter.clearConsole();
        return matcher.find();
    }

    // Password and username must be 6-20 characters.
    public static boolean validCredentialLength(String input){
        if (input.length() < 6 || input.length() > 20) {
            MenuPrinter.clearConsole();
            System.out.println("Invalid number of characters.");
            return false;
        } else{
            return true;
        }
    }
}
