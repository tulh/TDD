package com.kata.bankaccount.service;

import com.kata.bankaccount.dao.BankAccountDAO;
import com.kata.bankaccount.dao.TransactionDAO;
import com.kata.bankaccount.model.BankAccount;
import com.kata.bankaccount.model.Transaction;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Tu
 * Date: 7/23/13
 */
public class BankAccountService
{
    private BankAccountDAO bankAccountDAO;
    private TransactionDAO transactionDAO;
    private Date timeStamp;

    public Date getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp)
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

    public void openBankAccount(BankAccount bankAccount) throws SQLException
    {
        bankAccountDAO.save(bankAccount);
    }

    public BankAccount getAccount(String accountNumber) throws SQLException
    {
        return bankAccountDAO.findByAccountNumber(accountNumber);
    }

    public void doTransaction(String accountNumber, double amount, String description) throws SQLException
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

    public List<Transaction> getTransactionOccurred(String accountNumber)
    {
        return transactionDAO.findByAccountNumber(accountNumber);
    }

    public List<Transaction> getTransactionsOccurred(String accountNumber, Date startTime, Date stopTime)
    {
        List<Transaction> transactionList = transactionDAO.findByAccountNumber(accountNumber);
        List<Transaction> resultList = new ArrayList<Transaction>();
        if (transactionList.size() > 0)
        {
            for (Transaction transaction : transactionList)
            {
                if (transaction.getTimeStamp().after(startTime) &&
                        transaction.getTimeStamp().before(stopTime))
                {
                    resultList.add(transaction);
                }
            }
        }
        return resultList;
    }

    public List<Transaction> getNRecentTransaction(int n)
    {
        return transactionDAO.findNRecentTransaction(n);
    }
}
