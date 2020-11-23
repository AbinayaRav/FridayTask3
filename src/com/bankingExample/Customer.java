package com.bankingExample;

import java.util.Date;

public class Customer {
    private Integer accountNumber;
    private String customerName;
    private int age;
    private String dateOfBirth;
    private String address;
    private double accountBalance;


    public Customer()
    {

    }
    public Customer(Integer accountNumber, String customerName, int age, String dateOfBirth, String address, double accountBalance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.accountBalance = accountBalance;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "accountNumber=" + accountNumber +
                ", customerName='" + customerName + '\'' +
                ", age=" + age +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
