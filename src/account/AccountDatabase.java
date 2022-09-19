package account;

import java.util.HashMap;
import java.util.Map;

public class AccountDatabase {
    private static final Map<String, Account> allAccounts;
    private static int accountCounter;

    static {
        allAccounts = new HashMap<>();
        accountCounter = 0;
    }

    public static void addAccount(Account account) {
        allAccounts.put(account.getAccountNumber(), account);
        accountCounter++;
    }

    public static String generateNewAccountNumber() {
        String clearingNumber = "8888-";
        String accountNumber = String.valueOf(accountCounter + 1);

        return clearingNumber + accountNumber;
    }

    // Nesting -1
    public static Account getAccount(String accountNumber) {
        return allAccounts.getOrDefault(accountNumber, null);
    }

    // Literally there is no way to access this method (no points taken, just an observation)
    // Solution (sounds annoying but to solve this, you can create a separate jar file that has this feature, this jar is only given to the bank staff with some extra secutiry)
    public static Map<String, Account> getAllAccounts() {
        return allAccounts;
    }
}
