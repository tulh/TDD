package com.kata.bankaccount.model;

import java.util.Date;

/**
 * User: Tu
 * Date: 7/23/13
 */

public class BankAccount
{
    private String accountNumber;

    private double balance;

    private Date openTimeStamp;

    public BankAccount()
    {

    }

    public BankAccount(String accountNumber, double balance, Date instance)
    {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.openTimeStamp = instance;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public Date getOpenTimeStamp()
    {
        return openTimeStamp;
    }

    public void setOpenTimeStamp(Date openTimeStamp)
    {
        this.openTimeStamp = openTimeStamp;
    }
}
