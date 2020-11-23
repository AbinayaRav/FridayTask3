package com.bankingExample;


import java.util.Map;
import java.util.HashMap;

public class CustomerAccounts {

    private HashMap<Integer, Customer> accounts;
    public CustomerAccounts() {
        accounts = new HashMap<Integer, Customer>();
    }

    public void setAccounts(HashMap<Integer, Customer> accounts) {
        this.accounts = accounts;
    }

    public void addAccountToCollection(Customer customer)
    {
        accounts.put(customer.getAccountNumber(), customer);
    }

    public HashMap<Integer, Customer> getAccounts() {
        return accounts;
    }

    public Customer getSingleAccount(Integer accountNumber)
    {
        Customer customer = accounts.get(accountNumber);
        return customer;
    }

}
