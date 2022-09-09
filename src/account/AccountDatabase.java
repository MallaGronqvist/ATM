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

    public static Account getAccount(String accountNumber) {
        if (allAccounts.containsKey(accountNumber)){
            return allAccounts.get(accountNumber);
        } else {
            return null;
        }
    }

    public static Map<String, Account> getAllAccounts() {
        return allAccounts;
    }
}
