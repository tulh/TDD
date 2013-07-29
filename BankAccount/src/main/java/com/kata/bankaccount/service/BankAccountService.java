package com.kata.bankaccount.service;

import com.kata.bankaccount.dao.BankAccountDAO;
import com.kata.bankaccount.dao.TransactionDAO;
import com.kata.bankaccount.model.BankAccount;
import com.kata.bankaccount.model.Transaction;

import java.util.Calendar;

/**
 * User: Tu
 * Date: 7/23/13
 */
public class BankAccountService
{
    private BankAccountDAO bankAccountDAO;
    private TransactionDAO transactionDAO;
    private Calendar timeStamp;

    public Calendar getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(Calendar timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public TransactionDAO getTransactionDAO()
    {
        return transactionDAO;
    }

    public void setTransactionDAO(TransactionDAO transactionDAO)
    {
        this.transactionDAO = transactionDAO;
    }

    public BankAccountDAO getBankAccountDAO()
    {
        return bankAccountDAO;
    }

    public void setBankAccountDAO(BankAccountDAO bankAccountDAO)
    {
        this.bankAccountDAO = bankAccountDAO;
    }

    public BankAccount openBankAccount(BankAccount bankAccount)
    {
        return bankAccountDAO.save(bankAccount);
    }

    public BankAccount getAccount(String accountNumber)
    {
        return bankAccountDAO.findByAccountNumber(accountNumber);
    }

    public void doTransaction(String accountNumber, double amount, String description)
    {
        BankAccount bankAccount = bankAccountDAO.findByAccountNumber(accountNumber);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountDAO.save(bankAccount);
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTimeStamp(timeStamp);
        transactionDAO.save(transaction);
    }
}
