package com.kata.bankaccount.dao;

import com.kata.bankaccount.model.BankAccount;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Tu
 * Date: 7/23/13
 */
public class BankAccountDAO
{
    private Connection dbConnection;

    public BankAccountDAO(DataSource dataSource) throws SQLException
    {
        this.dbConnection = dataSource.getConnection();
    }
    public BankAccount findByAccountNumber(String accountNumber) throws SQLException
    {
        String queryString = "select * from bank_account where account_number='" + accountNumber + "'";
        ResultSet resultSet = dbConnection.createStatement().executeQuery(queryString);
        if(resultSet.next())
        {
            return new BankAccount(accountNumber, resultSet.getDouble("balance"), resultSet.getDate("open_timestamp"));
        }
        else return null;
    }

    public void save(BankAccount bankAccount) throws SQLException
    {
        String queryString = "insert into bank_account(account_number, balance, open_timestamp) values ("
            + bankAccount.getAccountNumber() + "," +bankAccount.getBalance() + "," +bankAccount.getOpenTimeStamp()+")";
        dbConnection.createStatement().executeUpdate(queryString);
    }

    public List<BankAccount> getAllBankAccount() throws SQLException
    {
        String queryString = "select * from bank_account";
        ResultSet resultSet = dbConnection.createStatement().executeQuery(queryString);
        List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
        while(resultSet.next())
        {
            bankAccounts.add(new BankAccount(resultSet.getString("account_number"), resultSet.getDouble("balance"), resultSet.getDate("open_timestamp")));
        }
        return bankAccounts;
    }
}
