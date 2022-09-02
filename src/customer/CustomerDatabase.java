package customer;

import customer.Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerDatabase {
    private static Map<String, Customer> allCustomers;
    private static int idCounter;

    static{
        allCustomers = new HashMap<>();
        idCounter = 0;
    }

    public static void addCustomer(Customer customer){
        customer.setCustomerID(++idCounter);
        allCustomers.put(customer.getUserName(), customer);
    }

    public static Customer getCustomer(String userName) throws NullPointerException{
        if(allCustomers.containsKey(userName)){
            return allCustomers.get(userName);
        } else throw new NullPointerException();
    }

    public static boolean userNameAlreadyTaken(String userName){
        return allCustomers.containsKey(userName);
    }
}
