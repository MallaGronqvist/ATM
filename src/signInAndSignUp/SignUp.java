package signInAndSignUp;

import account.Account;
import account.AccountDatabase;
import customer.Customer;
import customer.CustomerDatabase;
import menus.mainMenu.MainMenu;
import utils.InputReader;

import java.security.NoSuchAlgorithmException;

public class SignUp {

    public SignUp() {
        final String fullName = getFullName();
        final String userName = getUserName();

        Customer newCustomer = new Customer(fullName, userName);

        attemptSetPassword(newCustomer);

        assignAccount(newCustomer);

        CustomerDatabase.addCustomer(newCustomer);

        new MainMenu();
    }

    private void assignAccount(Customer newCustomer) {
        Account newAccount = new Account();

        newAccount.setAccountNumber(AccountDatabase.generateNewAccountNumber());

        newCustomer.setAccountNumber(newAccount.getAccountNumber());

        AccountDatabase.addAccount(newAccount);
    }

    public static void attemptSetPassword(Customer newCustomer) {
        final String passWord = getPassword();

        try {
            newCustomer.setPassword(passWord);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("An unexpected error has happened. You cannot create a password at the moment.");

            new MainMenu();
        }
    }

    public static String getUserName(){
        String userName = InputReader.requestTextInput("Choose a user name:");

        if (CustomerDatabase.userNameAlreadyTaken(userName)){
            System.out.println("The user name you entered is already taken. Choose another one.");
            userName = getUserName();
        }
        return userName;
    }

    public static String getFullName(){
        return InputReader.requestTextInput("Enter full name:");
    }

    public static String getPassword(){
        return InputReader.requestTextInput("Choose a password:");
    }
}
