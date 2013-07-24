package com.kata.bankaccount;

import java.util.Calendar;

/**
 * User: Tu
 * Date: 7/23/13
 */
public class BankAccount
{
    private String accountNumber;
    private double balance;
    private Calendar openTimeStamp;

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

    public Calendar getOpenTimeStamp()
    {
        return openTimeStamp;
    }

    public void setOpenTimeStamp(Calendar openTimeStamp)
    {
        this.openTimeStamp = openTimeStamp;
    }
}
