import com.kata.bankaccount.dao.BankAccountDAO;
import com.kata.bankaccount.dao.TransactionDAO;
import com.kata.bankaccount.model.BankAccount;
import com.kata.bankaccount.model.Transaction;
import com.kata.bankaccount.service.BankAccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
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
    @Test
    public void testOpenBankAccount() throws Exception
    {
        BankAccount newBankAccount = new BankAccount();
        newBankAccount.setAccountNumber("1234567890");
        newBankAccount.setBalance(0.0);
        newBankAccount.setOpenTimeStamp(Calendar.getInstance());
        bankAccountService.openBankAccount(newBankAccount);
        ArgumentCaptor<BankAccount> bankAccountCaptor = ArgumentCaptor.forClass(BankAccount.class);
        verify(bankAccountDAO).save(bankAccountCaptor.capture());
        assertEquals(accountNumber, bankAccountCaptor.getValue().getAccountNumber());
        assertEquals(0.0,bankAccountCaptor.getValue().getBalance());
    }

    @Test
    public void testGetAccountByAccountNumber() throws Exception
    {
        Mockito.when(bankAccountDAO.findByAccountNumber(Mockito.anyString())).then(new Answer<BankAccount>()
        {
            public BankAccount answer(InvocationOnMock invocationOnMock) throws Throwable
            {
                return new BankAccount(accountNumber, 0.0, Calendar.getInstance());
            }
        });
        assertEquals(accountNumber, bankAccountService.getAccount(accountNumber).getAccountNumber());
        assertEquals(0.0, bankAccountService.getAccount(accountNumber).getBalance());
    }

    @Test
    public void testDoDeposit() throws Exception
    {
        ArgumentCaptor<BankAccount> bankAccountArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
        when(bankAccountDAO.findByAccountNumber(accountNumber)).thenReturn(new BankAccount(accountNumber,0.0,Calendar.getInstance()));
        bankAccountService.doTransaction(accountNumber, 500.0, "add 500$");
        verify(bankAccountDAO,times(1)).save(bankAccountArgumentCaptor.capture());

        //add 1st time 500$
        bankAccountService.getAccount(accountNumber);
        assertEquals(500.0, bankAccountArgumentCaptor.getValue().getBalance());

        //add more 2nd time 1000$
        bankAccountService.doTransaction(accountNumber, 1000.0, "add 1000$");
        verify(bankAccountDAO,times(2)).save(bankAccountArgumentCaptor.capture());
        assertEquals(1500.0, bankAccountArgumentCaptor.getValue().getBalance());
    }

    @Test
    public void testSaveTransaction() throws Exception
    {
        ArgumentCaptor<BankAccount> bankAccountArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
        when(bankAccountDAO.findByAccountNumber(accountNumber)).thenReturn(new BankAccount(accountNumber,0.0,Calendar.getInstance()));
        ArgumentCaptor<Transaction> transactionArgumentCaptor = ArgumentCaptor.forClass(Transaction.class);
        bankAccountService.doTransaction(accountNumber, 500.0, "add 500$");
        verify(bankAccountDAO,times(1)).save(bankAccountArgumentCaptor.capture());
        verify(transactionDAO).save(transactionArgumentCaptor.capture());
        assertEquals(500.0,transactionArgumentCaptor.getValue().getAmount());
    }

    @Test
    public void testDoWithDraw() throws Exception
    {
        ArgumentCaptor<BankAccount> bankAccountArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
        when(bankAccountDAO.findByAccountNumber(accountNumber)).thenReturn(new BankAccount(accountNumber, 5000.0, Calendar.getInstance()));

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
        when(bankAccountDAO.findByAccountNumber(accountNumber)).thenReturn(new BankAccount(accountNumber,1000.0,timeStamp.getInstance()));
        when(timeStamp.getTimeInMillis()).thenReturn(1000L);
        bankAccountService.setTimeStamp(timeStamp);
        ArgumentCaptor<Transaction> transactionArgumentCaptor = ArgumentCaptor.forClass(Transaction.class);
        bankAccountService.doTransaction(accountNumber, -500.0, "minus 500$");
        verify(bankAccountDAO, times(1)).save(bankAccountArgumentCaptor.capture());
        verify(transactionDAO).save(transactionArgumentCaptor.capture());
        assertEquals(-500.0, transactionArgumentCaptor.getValue().getAmount());

        assertEquals(1000L,transactionArgumentCaptor.getValue().getTimeStamp().getTimeInMillis());
    }

    @Test
    public void testGetAllTransaction() throws Exception
    {
        List<Transaction> transactionList = new ArrayList<Transaction>();
        transactionList.add(new Transaction(accountNumber, 300.0, "deposit 300$"));
        when(transactionDAO.getAll(accountNumber)).thenReturn(transactionList);
        assertEquals(transactionList,bankAccountService.getTransactionOccurred(accountNumber));

        assertFalse(transactionList.equals(bankAccountService.getTransactionOccurred(anotherAccountNumber)));
    }


}
