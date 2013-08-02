package com.kata.bankaccount.dao;

import com.kata.bankaccount.model.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * User: Tu
 * Date: 7/24/13
 */
public class TransactionDAO
{
    private Connection dbConnection;

    public TransactionDAO(DataSource dataSource) throws SQLException
    {
        this.dbConnection = dataSource.getConnection();
    }

    public List<Transaction> findByAccountNumber(String accountNumber)
    {
        return null;
    }

    public List<Transaction> findNRecentTransaction(int n)
    {
        return null;
    }

    public void save(Transaction transaction) throws SQLException
    {
        String queryString = "insert into transaction(account_number, timestamp, amount, number) values ("
                + transaction.getAccountNumber() + "," + transaction.getTimeStamp() + "," +  transaction.getAmount()+
                transaction.getDescription() + ")";
        dbConnection.createStatement().executeUpdate(queryString);
    }
}
