package customerOperations.customerAuthentication;

import customer.Customer;
import customer.CustomerDatabase;
import utils.MenuPrinter;

import java.util.List;
import java.util.Scanner;

public class ChangeCredentials {
    Customer customer;

    public ChangeCredentials(Customer customer) {
        this.customer = customer;

        displayOptions(List.of("Change username", "Change password"));
        readUserInput();
    }

    private static void changeUsername(Customer customer) {

        CustomerDatabase.updateUsername(customer);

        System.out.println("Your username was changed successfully.");

    }

    private static void changePassword(Customer customer) {

        SignUp.attemptSetPassword(customer);

        System.out.println("Your password was set successfully.");

    }

    private void displayOptions(List<String> menuOptions) {
        System.out.println("***Change credentials***");
        System.out.println("Choose an operation:");
        MenuPrinter.listOptions(menuOptions);
        requestUserInput();
    }

    public void requestUserInput() {
        System.out.print("Enter your choice and press enter: ");
    }

    public void readUserInput() {
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();

        try {
            int selectedOption = Integer.parseInt(input);

            processOption(selectedOption);

        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            printInvalidOption();
            requestUserInput();
            readUserInput();
        }
    }

    public void printInvalidOption() {
        System.out.println("You entered an invalid option.");
    }

    public void processOption(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
            case 1 -> changeUsername(customer);
            case 2 -> changePassword(customer);
            default -> throw new IndexOutOfBoundsException();
        }
    }
}
