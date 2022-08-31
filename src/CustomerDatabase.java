import java.util.HashMap;
import java.util.Map;

public class CustomerDatabase {
    private static Map<String, Customer> allCustomers;
    private static int idCounter;

    public CustomerDatabase() {
        this.allCustomers = new HashMap<>();
        this.idCounter = 0;
    }

    public static void addCustomer(Customer customer){
        customer.setCustomerID(++idCounter);
        allCustomers.put(customer.getUserName(), customer);
    }

    public Customer getCustomer(String userName){
        return allCustomers.get(userName);
    }

    public static boolean userNameAlreadyTaken(String userName){
        return allCustomers.containsKey(userName);
    }
}
