import com.kata.bankaccount.dao.BankAccountDAO;
import com.kata.bankaccount.dao.TransactionDAO;
import com.kata.bankaccount.model.BankAccount;
import com.kata.bankaccount.model.Transaction;
import com.kata.bankaccount.service.BankAccountService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

/**
 * User: Tu
 * Date: 7/23/13
 */
public class TestBankAccount
{

    private final static String accountNumber = "1234567890";
    private final static String anotherAccountNumber = "jjkhkh18492124";
    private BankAccountDAO bankAccountDAO = mock(BankAccountDAO.class);
    private TransactionDAO transactionDAO = mock(TransactionDAO.class);
    private BankAccountService bankAccountService;
    private Calendar timeStamp = mock(Calendar.class);

    @Before
    public void setup()
    {
        bankAccountService = new BankAccountService();
        bankAccountService.setBankAccountDAO(bankAccountDAO);
        bankAccountService.setTransactionDAO(transactionDAO);
        bankAccountService.setTimeStamp(timeStamp);
    }

    public void createDefaultRecordForBankAccount(double money)
    {
        when(bankAccountDAO.findByAccountNumber(accountNumber)).
                thenReturn(new BankAccount(accountNumber, money, Calendar.getInstance()));
    }

    @Test
    public void testOpenBankAccount() throws Exception
    {
        BankAccount newBankAccount = new BankAccount();
        newBankAccount.setAccountNumber("1234567890");
        newBankAccount.setBalance(0.0);
        newBankAccount.setOpenTimeStamp(timeStamp);
        bankAccountService.openBankAccount(newBankAccount);
        ArgumentCaptor<BankAccount> bankAccountCaptor = ArgumentCaptor.forClass(BankAccount.class);
        verify(bankAccountDAO).save(bankAccountCaptor.capture());
        assertEquals(accountNumber, bankAccountCaptor.getValue().getAccountNumber());
        assertEquals(0.0, bankAccountCaptor.getValue().getBalance());
    }

    @Test
    public void testGetAccountByAccountNumber() throws Exception
    {
        createDefaultRecordForBankAccount(0.0);
        assertEquals(accountNumber, bankAccountService.getAccount(accountNumber).getAccountNumber());
        assertEquals(0.0, bankAccountService.getAccount(accountNumber).getBalance());
    }

    @Test
    public void testDoDeposit() throws Exception
    {
        ArgumentCaptor<BankAccount> bankAccountArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
        createDefaultRecordForBankAccount(0.0);
        bankAccountService.doTransaction(accountNumber, 500.0, "add 500$");
        verify(bankAccountDAO, times(1)).save(bankAccountArgumentCaptor.capture());

        //add 1st time 500$
        bankAccountService.getAccount(accountNumber);
        assertEquals(500.0, bankAccountArgumentCaptor.getValue().getBalance());

        //add more 2nd time 1000$
        bankAccountService.doTransaction(accountNumber, 1000.0, "add 1000$");
        verify(bankAccountDAO, times(2)).save(bankAccountArgumentCaptor.capture());
        assertEquals(1500.0, bankAccountArgumentCaptor.getValue().getBalance());
    }

    @Test
    public void testSaveTransaction() throws Exception
    {
        ArgumentCaptor<BankAccount> bankAccountArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
        createDefaultRecordForBankAccount(0.0);
        ArgumentCaptor<Transaction> transactionArgumentCaptor = ArgumentCaptor.forClass(Transaction.class);
        bankAccountService.doTransaction(accountNumber, 500.0, "add 500$");
        verify(bankAccountDAO, times(1)).save(bankAccountArgumentCaptor.capture());
        verify(transactionDAO).save(transactionArgumentCaptor.capture());
        assertEquals(500.0, transactionArgumentCaptor.getValue().getAmount());
    }

    @Test
    public void testDoWithDraw() throws Exception
    {
        ArgumentCaptor<BankAccount> bankAccountArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
        createDefaultRecordForBankAccount(5000.0);
        // withdraw first time
        bankAccountService.doTransaction(accountNumber, -250.0, "minus 250$");
        verify(bankAccountDAO, times(1)).save(bankAccountArgumentCaptor.capture());
        assertEquals(4750.0, bankAccountArgumentCaptor.getValue().getBalance());

        // withdraw second time
        bankAccountService.doTransaction(accountNumber, -250.0, "minus 250$");
        verify(bankAccountDAO, times(2)).save(bankAccountArgumentCaptor.capture());
        assertEquals(4500.0, bankAccountArgumentCaptor.getValue().getBalance());

        // deposit
        bankAccountService.doTransaction(accountNumber, 500.0, "plus 500$");
        verify(bankAccountDAO, times(3)).save(bankAccountArgumentCaptor.capture());
        assertEquals(5000.0, bankAccountArgumentCaptor.getValue().getBalance());
    }

    @Test
    public void testSaveWithDrawTransaction() throws Exception
    {
        ArgumentCaptor<BankAccount> bankAccountArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
        createDefaultRecordForBankAccount(1000.0);
        when(timeStamp.getTimeInMillis()).thenReturn(1000L);
        bankAccountService.setTimeStamp(timeStamp);
        ArgumentCaptor<Transaction> transactionArgumentCaptor = ArgumentCaptor.forClass(Transaction.class);
        bankAccountService.doTransaction(accountNumber, -500.0, "minus 500$");
        verify(bankAccountDAO, times(1)).save(bankAccountArgumentCaptor.capture());
        verify(transactionDAO).save(transactionArgumentCaptor.capture());
        assertEquals(-500.0, transactionArgumentCaptor.getValue().getAmount());

        assertEquals(1000L, transactionArgumentCaptor.getValue().getTimeStamp().getTimeInMillis());
    }

    @Test
    public void testGetAllTransaction() throws Exception
    {
        List<Transaction> transactionList = new ArrayList<Transaction>();
        transactionList.add(new Transaction(accountNumber, 300.0, "deposit 300$"));
        when(transactionDAO.findByAccountNumber(accountNumber)).thenReturn(transactionList);
        assertEquals(transactionList, bankAccountService.getTransactionOccurred(accountNumber));

        assertFalse(transactionList.equals(bankAccountService.getTransactionOccurred(anotherAccountNumber)));
    }

    @Test
    public void testGetAllTransactionBetweenTwoMoments() throws Exception
    {
        List<Transaction> transactionList = new ArrayList<Transaction>();
        Transaction tran = new Transaction();
        tran.setTimeStamp(timeStamp);
        transactionList.add(tran);
        when(timeStamp.getTimeInMillis()).thenReturn(1000L);
        when(transactionDAO.findByAccountNumber(accountNumber)).thenReturn(transactionList);
        long startTime = 1000L;
        long stopTime = 2000L;
        assertNotNull(bankAccountService.getTransactionsOccurred(accountNumber, startTime, stopTime));
        assertEquals(transactionList, bankAccountService.getTransactionsOccurred(accountNumber, startTime, stopTime));
        assertNotSame(transactionList, bankAccountService.getTransactionsOccurred(accountNumber, 1001L, 1040L));
    }

    @Test
    public void testGetNRecentTransaction() throws Exception
    {
        List<Transaction> transactionList = new ArrayList<Transaction>();
        Transaction tran = new Transaction();
        tran.setTimeStamp(timeStamp);
        transactionList.add(tran);
        when(transactionDAO.findNRecentTransaction(10)).thenReturn(transactionList);
        assertNotNull(bankAccountService.getNRecentTransaction(10));
        assertNotSame(10, bankAccountService.getNRecentTransaction(10).size());
        assertTrue(bankAccountService.getNRecentTransaction(10).size() == 1);
    }

    @After
    public void tearDown()
    {
        reset(bankAccountDAO, transactionDAO, timeStamp);
    }

}
