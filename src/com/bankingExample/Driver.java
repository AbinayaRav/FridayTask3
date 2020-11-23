package com.bankingExample;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws IOException, ParseException {
        AccountOperation acc = new AccountOperation();
        CustomerAccounts customerAccounts = new CustomerAccounts();
        HashMap<Integer, Customer> accounts = new HashMap<>();
        customerAccounts = acc.retrieveAllAccountHolders();
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        System.out.println("******************************************************************************************************************");
        System.out.println("                                                  WELCOME                                                         ");
        System.out.println("******************************************************************************************************************\n");

        while (flag) {
            System.out.println("\n\nPlease select one of the following :   \n 1. Create a new Account \n 2. Retrieve all the accounts \n 3. Deposit money to the Accountholder's account" +
                    "\n 4. Withdraw Amount from the Account \n 5. Delete an Account \n 6. Exit\n");
            int select = scan.nextInt();
            int accNo = 0;
            if (select == 1) {
                System.out.println("Welcome!! Please enter your account number : ");

                accNo = scan.nextInt();
                if (customerAccounts.getAccounts().containsKey(accNo)) {
                    System.out.println("Account already exists..... What can we do for you today? ");
                    continue;
                } else {
                    System.out.println("Welcome new Customer!");
                    Customer customer = new Customer();
                    System.out.println("Please enter your name :");
                    customer.setCustomerName(scan.next());
                    System.out.println("Please enter your Age :");
                    customer.setAge(scan.nextInt());
                    System.out.println("Please enter your Date of Birth (MM/dd/yyyy):");
                    customer.setDateOfBirth(scan.next());
                    System.out.println("Please enter your Address :");
                    customer.setAddress(scan.next());
                    boolean flag1 = true;
                    while (flag1) {
                        System.out.println("Please enter the initial deposit amount : ");
                        customer.setAccountBalance(scan.nextInt());
                        if (customer.getAccountBalance() <= 0) {
                            System.out.println("Deposit amount should be greater than 0");
                            continue;
                        } else {
                            flag1 = false;
                        }
                        acc.createCustomerAccount(customer);
                    }
                }
            } else if (select == 2) {
                System.out.println("***************************************** Printing all the Account holders Data *************************************\n");
                System.out.println("-------------------------------------------------------------------------------------------------------------------");
                System.out.println("AccountNumber | CustomerName | Age | DateOfBirth | Address | AccountBalance");
                System.out.println("-------------------------------------------------------------------------------------------------------------------");
                for (Integer i : customerAccounts.getAccounts().keySet()) {
                    Customer cust = new Customer();
                    cust = customerAccounts.getSingleAccount(i);
                    System.out.println(cust.getAccountNumber() + "\t\t\t" + cust.getCustomerName() + "\t\t" + cust.getAge() + "\t\t" + cust.getDateOfBirth() + "\t\t"
                            + cust.getAddress() + "\t\t\t" + cust.getAccountBalance());

                }
                System.out.println();

            } else if (select == 3) {
                {
                    System.out.println("Enter your account number : ");
                    accNo = scan.nextInt();
                    System.out.println("Enter the amount to be deposited : ");
                    int deposit_amount = scan.nextInt();
                    acc.depositAmount(accNo, deposit_amount);
                }
            } else if (select == 4) {
                Customer customer2 = new Customer();
                System.out.println("Enter your account number : ");
                accNo = scan.nextInt();
                System.out.println("Enter the amount to be withdrawn : ");
                int amountToBeWithdrawn = scan.nextInt();
                acc.withdrawAmount(accNo, amountToBeWithdrawn);
            } else if (select == 5) {
                System.out.println("Are you sure you want to delete the account ??");
                String t = scan.next();
                if (t.equalsIgnoreCase("y")) {
                    System.out.println("Enter the account number : ");
                    accNo = scan.nextInt();
                    acc.deleteExistingAccount(accNo);
                }
            } else if (select == 6) {
                System.out.println("**********************************   THANK YOU *******************************************************");
                System.exit(0);
            }
            System.out.println("Do you wish to continue? (Y/N) ");
            String c = scan.next();
            if (c.equalsIgnoreCase("y")) {
            } else {
                System.exit(0);
            }
            continue;
        }
    }
}
