package com.kata.bankaccount.service;

import com.kata.bankaccount.dao.BankAccountDAO;
import com.kata.bankaccount.dao.TransactionDAO;
import com.kata.bankaccount.model.BankAccount;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.Assert.assertTrue;

/**
 * User: Tu
 * Date: 7/30/13
 */
public class BankAccountServiceTest
{
    private BankAccountDAO bankAccountDAO;

    private TransactionDAO transactionDAO;

    private BankAccountService bankAccountService;

    //using H2 so that we can create in-memory database for testing
    // without having to install any DBMS software
    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    // create the db tables
    @BeforeClass
    public static void createSchema() throws Exception
    {
        String schemaFileName = System.class.getResource("/schema.sql").toString().substring(6);
        RunScript.execute(JDBC_URL, USER, PASSWORD, schemaFileName, Charset.forName("UTF8"), false);

    }

    private DataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(JDBC_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }
    // populate the table with test data
    @Before
    public void importDataSet() throws Exception {
        IDataSet dataSet = readDataSet();  // read data from xml file
        cleanlyInsert(dataSet);  // empty the db and insert data
        bankAccountDAO = new BankAccountDAO(dataSource());
        transactionDAO = new TransactionDAO(dataSource());
    }

    private IDataSet readDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(System.class.getResource("/dataset.xml"));
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testOpenBankAccount() throws Exception
    {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber("1234567890");
        bankAccount.setBalance(200.5);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate = simpleDateFormat.parse("2013-07-31 14:24:00") ;
        bankAccount.setOpenTimeStamp(newDate);
        bankAccountDAO.save(bankAccount);

    }

    @Test
    public void testGetAccount() throws Exception
    {
        BankAccount bankAccount = bankAccountDAO.findByAccountNumber("1234567890");
        assertTrue(100.0==bankAccount.getBalance());
        bankAccountDAO.save(new BankAccount());
        System.out.println(bankAccountDAO.getAllBankAccount().size());
    }

    @Test
    public void testDoTransaction() throws Exception
    {

    }

    @Test
    public void testGetTransactionOccurred() throws Exception
    {

    }

    @Test
    public void testGetTransactionsOccurred() throws Exception
    {

    }

    @Test
    public void testGetNRecentTransaction() throws Exception
    {

    }
}
