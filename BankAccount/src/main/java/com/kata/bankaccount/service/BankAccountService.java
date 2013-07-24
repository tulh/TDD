package com.kata.bankaccount.service;

import com.kata.bankaccount.BankAccount;
import com.kata.bankaccount.dao.BankAccountDAO;

/**
 * User: Tu
 * Date: 7/23/13
 */
public class BankAccountService
{
    private BankAccountDAO bankAccountDAO;

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
}
