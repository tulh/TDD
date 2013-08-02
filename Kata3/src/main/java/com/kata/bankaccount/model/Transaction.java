package com.kata.bankaccount.model;

import java.sql.Date;


/**
 * User: Tu
 * Date: 7/24/13
 */
public class Transaction
{
    private String accountNumber;

    private Date timeStamp;

    private double amount;

    private String description;

    public Transaction(String accountNumber, double amount, String description)
    {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.description = description;
    }

    public Transaction()
    {
        //To change body of created methods use File | Settings | File Templates.
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public Date getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
