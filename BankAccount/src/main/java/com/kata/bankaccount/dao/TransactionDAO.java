package com.kata.bankaccount.dao;

import com.kata.bankaccount.model.Transaction;

import java.util.List;

/**
 * User: Tu
 * Date: 7/24/13
 */
public class TransactionDAO
{
    public Transaction save(Transaction transaction)
    {
        return transaction;
    }

    public List<Transaction> findByAccountNumber(String accountNumber)
    {
        return null;
    }

    public List<Transaction> findNRecentTransaction(int n)
    {
        return null;
    }
}
