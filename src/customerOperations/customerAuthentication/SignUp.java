package customerOperations.customerAuthentication;

import customer.Customer;
import customer.CustomerDatabase;
import menus.mainMenu.MainMenu;
import utils.InputReader;
import utils.MenuPrinter;

import java.security.NoSuchAlgorithmException;

import static utils.InputReader.checkForDiscontinuedInput;
import static utils.InputReader.discontinuedInput;


public class SignUp {

    public SignUp() {
        final String fullName = promptCustomerForFullName();

        checkForDiscontinuedInput(fullName);

        final String userName = promptCustomerForUserName();

        checkForDiscontinuedInput(userName);

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

        if (discontinuedInput(firstName)) { return firstName; }

        MenuPrinter.clearConsole();

        String lastName = InputReader.requestInput("Enter last name.", "name");

        if (discontinuedInput(lastName)) { return lastName; }

        String fullName = firstName + " " + lastName;

        // Trim eventual extra whitespace that the customer has entered.
        fullName = fullName.trim().replaceAll(" +", " ");

        if (!fullNameCorrect(fullName)) {
            fullName = promptCustomerForFullName();
        }
        MenuPrinter.clearConsole();

        return fullName;
    }

    private static boolean fullNameCorrect(String fullName) {
        MenuPrinter.clearConsole();
        System.out.println("You entered the following name: " + fullName);
        System.out.println();
        System.out.println("You won't be able to edit this name after signing up.");
        System.out.println("Is this name correct?");
        return InputReader.getConfirmation();
    }

    public static String promptCustomerForUserName() {
        String userName = InputReader.requestInput("Choose a username.", "credential");

        if (discontinuedInput(userName)) {
            return userName;
        }

        if (CustomerDatabase.userNameAlreadyTaken(userName)) {
            MenuPrinter.clearConsole();
            System.out.println("The username you entered is already taken. Choose another one.");
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

        String password = InputReader.requestInput("Choose a password.", "credential");

        return password;
    }
}
