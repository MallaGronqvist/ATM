package customer.customerAuthentication;

import customer.Customer;
import customer.CustomerDatabase;
import menus.mainMenu.MainMenu;
import utils.InputReader;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.InputReader.checkForDiscontinuedOperation;

public class SignUp {

    public SignUp() {
        final String fullName = promptCustomerForFullName();
        final String userName = promptCustomerForUserName();
        Customer newCustomer = new Customer(fullName, userName);

        attemptSetPassword(newCustomer);

        newCustomer.assignAccount();

        CustomerDatabase.addCustomer(newCustomer);

        new MainMenu();
    }

    private static String promptCustomerForFullName() {
        String firstName = InputReader.requestTextInput("Enter first name: ");
        checkForDiscontinuedOperation(firstName);

        while (!validateLetters((firstName))) {
            firstName = InputReader.requestTextInput("You entered invalid characters. Try again. \nEnter first name:");
        }

        String lastName = InputReader.requestTextInput("Enter last name:");
        checkForDiscontinuedOperation(lastName);

        while (!validateLetters((lastName))) {
            lastName = InputReader.requestTextInput("You entered invalid characters. Try again. \nEnter last name:");
        }

        String fullName = firstName + " " + lastName;
        // Trim eventual extra whitespace that the customer has entered.
        fullName = fullName.trim().replaceAll(" +", " ");

        if (!fullNameCorrect(fullName)) {
            fullName = promptCustomerForFullName();
        }
        return fullName;
    }

    private static boolean fullNameCorrect(String fullName) {
        System.out.println("You entered following name: " + fullName);
        System.out.println("You won't be able to edit this name after signing up.");
        System.out.println("Is this name correct?");
        return InputReader.getConfirmation();
    }

    private static boolean validateLetters(String input) {
        // Validate input using \\p{L} which is a Unicode Character Property
        // that matches any kind of letter from any language.
        String allowedCharacters = "^[\\p{L}\\s'-]+$";
        Pattern pattern = Pattern.compile(allowedCharacters, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static String promptCustomerForUserName() {
        String userName = InputReader.requestTextInput("Choose a user name. Whitespace is not allowed:");
        checkForDiscontinuedOperation(userName);

        if (userName.contains(" ")) {
            userName = promptCustomerForUserName();
        }

        if (CustomerDatabase.userNameAlreadyTaken(userName)) {
            System.out.println("The user name you entered is already taken. Choose another one.");
            userName = promptCustomerForUserName();
        }
        return userName;
    }

    public static void attemptSetPassword(Customer customer) {
        final String password = promptCustomerForPassword();
        checkForDiscontinuedOperation(password);

        try {
            customer.setPassword(password);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("An unexpected error has happened. You cannot create a password at the moment.");
            new MainMenu();
        }
    }

    private static String promptCustomerForPassword() {
        String password = InputReader.requestTextInput("Choose a password. Whitespace is not allowed." +
                "Allowed length is 6 - 20 characters: ");
        checkForDiscontinuedOperation(password);

        if (password.contains(" ")) {
            password = promptCustomerForPassword();
        }

        if (password.length() < 6 || password.length() > 20) {
            System.out.println("Invalid number of characters in password. Try again.");
            password = promptCustomerForPassword();
        }
        return password;
    }
}
