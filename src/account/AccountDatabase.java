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

    // Ask if this should use NullPointerException
    public static Account getAccount(String accountNumber) {
        return allAccounts.get(accountNumber);
    }

    public static Map<String, Account> getAllAccounts() {
        return allAccounts;
    }
}
