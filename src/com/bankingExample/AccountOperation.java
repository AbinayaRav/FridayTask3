package com.bankingExample;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class AccountOperation implements CustomerActivities {

    private CustomerAccounts customerAccounts = new CustomerAccounts();
    UpdateExcelFile update = new UpdateExcelFile();

    @Override
    public void createCustomerAccount(Customer customer) {
        try {
            int max = 0;
            for (int k : customerAccounts.getAccounts().keySet()) {
                if (k > max) {
                    max = k;
                }
            }
            customer.setAccountNumber(++max);
            customerAccounts.addAccountToCollection(customer);
            update.writeToExcel(customer, "CREATE NEW RECORD");
            System.out.println("----------------------------Account created successfully--------------------------------------------\n");
        } catch (Exception e) {
            System.out.println("Problem in creating a new account. Please try again \n" + e.getMessage());
        }
    }

    @Override
    public void withdrawAmount(int accNo, double amountToBeWithdrawn) {
        try {
            Customer customer = new Customer();
            customer = customerAccounts.getSingleAccount(accNo);
            if (customer.getAccountBalance() > 0.0) {
                double difference = customer.getAccountBalance() - amountToBeWithdrawn;
                if (difference > 0) {
                    customer.setAccountBalance(difference);
                    customerAccounts.getAccounts().put(accNo, customer);
                    update.writeToExcel(customer, "WITHDRAW AMOUNT");
                    System.out.println("----------------------------Amount has been successfully withdrawn from the Account Number : " + accNo + " --------------------------------------------");
                    System.out.println("OUTSTANDING BALANCE IS : " + customer.getAccountBalance() + "\n");
                } else {
                    System.out.println("Amount can't be withdrawn more than the balance. Try withdrawing less amount!!\n");
                }

            } else {
                System.out.println("Amount Balance can never be zero or negative!!!!!! Please deposit some amount to your account\n");
            }
        } catch (Exception e) {
            System.out.println("Problem in withdrawing amount from the particular account. Please try again!! \n" + e.getMessage());
        }
    }

    @Override
    public void depositAmount(int accNo, double deposit) {
        try {
            Customer customer = new Customer();
            customer = customerAccounts.getSingleAccount(accNo);
            customer.setAccountBalance(customer.getAccountBalance() + deposit);
            customerAccounts.getAccounts().put(accNo, customer);
            update.writeToExcel(customer, "UPDATE AMOUNT BALANCE");
            System.out.println("----------------------------Amount has been successfully deposited to the Account Number : " + accNo + "--------------------------------------------");
            System.out.println("OUTSTANDING BALANCE IS : " + customer.getAccountBalance() + "\n");
        } catch (Exception e) {
            System.out.println("Problem in depositing amount to the particular account. Please try again!! \n" + e.getMessage());
        }
    }

    @Override
    public void deleteExistingAccount(int accNo) {
        try {
            Customer cust = customerAccounts.getAccounts().get(accNo);
            customerAccounts.getAccounts().remove(accNo);
            update.writeToExcel(cust, "DELETE ACCOUNT");
            System.out.println("---------------------------- Account has been deleted --------------------------------------------\n");
        } catch (Exception e) {
            System.out.println("Uanble to delete the account due to some reason. Please try again!! \n" + e.getMessage());
        }

    }

    public CustomerAccounts retrieveAllAccountHolders() throws IOException {
        try {
            HashMap<Integer, Customer> accounts = new HashMap<>();
            File file = new File("src/SampleData.xlsx");
            FileInputStream input = new FileInputStream(file);
            Workbook hssf = new XSSFWorkbook(input);
            Sheet sheet = hssf.getSheetAt(0);
            Iterator<Row> iter1 = sheet.iterator();
            while (iter1.hasNext()) {
                Iterator<Cell> currentRow = iter1.next().iterator();
                Customer customer = new Customer();
                while (currentRow.hasNext()) {
                    Cell currentCell = currentRow.next();
                    if ((currentCell.getColumnIndex() == 0) && (currentCell.getCellType() == CellType.NUMERIC)) {
                        customer.setAccountNumber((int) currentCell.getNumericCellValue());
                    } else if ((currentCell.getColumnIndex() == 1) && (currentCell.getCellType() == CellType.STRING)) {
                        customer.setCustomerName(currentCell.getStringCellValue());
                    } else if ((currentCell.getColumnIndex() == 2) && (currentCell.getCellType() == CellType.NUMERIC)) {
                        customer.setAge((int) currentCell.getNumericCellValue());
                    } else if ((currentCell.getColumnIndex() == 3) && (currentCell.getCellType() == CellType.STRING)) {
                        customer.setDateOfBirth(currentCell.getStringCellValue());
                    } else if ((currentCell.getColumnIndex() == 4) && (currentCell.getCellType() == CellType.STRING)) {
                        customer.setAddress(currentCell.getStringCellValue());
                    } else if ((currentCell.getColumnIndex() == 5) && (currentCell.getCellType() == CellType.NUMERIC)) {
                        customer.setAccountBalance((int) currentCell.getNumericCellValue());
                    }
                    accounts.put(customer.getAccountNumber(), customer);
                }
                System.out.println();
            }
            accounts.remove(null);
            customerAccounts.setAccounts(accounts);
        } catch (Exception e) {
            System.out.println("Can't retrieve the account details from the xlsx file. Please try again!! \n" + e.getMessage());
        }
        return customerAccounts;
    }
}
