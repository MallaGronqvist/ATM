package signInAndSignUp;

import menus.mainMenu.MainMenu;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SignUp {

    public SignUp() {
        final String fullName = getFullName();
        final String userName = getUserName();
        final String passWord = getPassword();

        Customer newCustomer = new Customer(fullName, userName);
        try {
            newCustomer.setPassword(passWord);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("An unexpected error has happened. You cannot create an account at the moment.");

            new MainMenu();
        }
        Account newAccount = new Account();
        newAccount.setAccountNumber(AccountDatabase.generateNewAccountNumber());
        newCustomer.setAccountNumber(newAccount.getAccountNumber());
        CustomerDatabase.addCustomer(newCustomer);
        AccountDatabase.addAccount(newAccount);

        new MainMenu();
    }

    public static String getUserName(){
        String userName = InputReader.requestTextInput("Enter user name:");
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
        return InputReader.requestTextInput("Enter password:");
    }
}
