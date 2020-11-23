package com.bankingExample;

public interface CustomerActivities {

    void createCustomerAccount(Customer customer);
    void withdrawAmount(int accNo, double amountToBeWithdrawn);
    void depositAmount(int accNo, double deposit);
    void deleteExistingAccount(int accNo);
}
