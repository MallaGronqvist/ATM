package signInAndSignUp;

import signInAndSignUp.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountDatabase {
    private static Map<String, Account> allAccounts = new HashMap<>();
    private static int accountCounter;

    public static void addAccount(Account account){
        allAccounts.put(account.getAccountNumber(), account);
        accountCounter++;
    }

    public static String generateNewAccountNumber(){
        String clearingNumber = "8888-";
        String accountNumber = String.valueOf(accountCounter + 1);

        return clearingNumber + accountNumber;
    }
}
