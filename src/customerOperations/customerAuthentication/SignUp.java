package customerOperations.customerAuthentication;

import customer.Customer;
import customer.CustomerDatabase;
import menus.mainMenu.MainMenu;
import utils.InputReader;
import utils.MenuPrinter;

import java.security.NoSuchAlgorithmException;

import static utils.InputReader.checkForDiscontinuedInput;
import static utils.InputReader.validateLetters;

public class SignUp {

    public SignUp() {
        final String fullName = promptCustomerForFullName();

        final String userName = promptCustomerForUserName();
        Customer newCustomer = new Customer(fullName, userName);
        MenuPrinter.clearConsole();
        attemptSetPassword(newCustomer);

        newCustomer.assignAccount();

        CustomerDatabase.addCustomer(newCustomer);

        new MainMenu();
    }

    private static String promptCustomerForFullName() {
        MenuPrinter.clearConsole();
        String firstName = InputReader.requestInput("Enter first name. ", "name");
        checkForDiscontinuedInput(firstName);

        while (!validateLetters((firstName))) {
            firstName = InputReader.requestInput("You entered invalid characters. Try again. \nEnter first name.", "name");
        }

        MenuPrinter.clearConsole();
        String lastName = InputReader.requestInput("Enter last name", "name");

        checkForDiscontinuedInput(lastName);

        while (!validateLetters((lastName))) {
            lastName = InputReader.requestInput("You entered invalid characters. Try again. \nEnter last name.", "name");
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
        MenuPrinter.clearConsole();
        System.out.println("You entered following name: " + fullName);
        System.out.println();
        System.out.println("You won't be able to edit this name after signing up.");
        System.out.println("Is this name correct?");
        return InputReader.getConfirmation();
    }

    public static String promptCustomerForUserName() {
        String userName = InputReader.requestInput("Choose a username.", "credential");

        checkForDiscontinuedInput(userName);

        if (userName.contains(" ")) {
            MenuPrinter.clearConsole();
            System.out.println("Invalid input.");
            userName = promptCustomerForUserName();
        }

        if (CustomerDatabase.userNameAlreadyTaken(userName)) {
            MenuPrinter.clearConsole();
            System.out.println("The user name you entered is already taken. Choose another one.");
            userName = promptCustomerForUserName();
        }
        return userName;
    }

    public static void attemptSetPassword(Customer customer) {
        final String password = promptCustomerForPassword();

        checkForDiscontinuedInput(password);

        try {
            customer.setPassword(password);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("An unexpected error has happened. You cannot create a password at the moment.");
            new MainMenu();
        }
    }

    private static String promptCustomerForPassword() {
        MenuPrinter.clearConsole();

        String password = InputReader.requestInput("Choose a password." +
                "\nAllowed length is 6 - 20 characters. ", "credential");

        checkForDiscontinuedInput(password);

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
