import com.kata.bankaccount.model.BankAccount;
import com.kata.bankaccount.dao.BankAccountDAO;
import com.kata.bankaccount.service.BankAccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * User: Tu
 * Date: 7/23/13
 */
public class TestBankAccount
{

    private final static String accountNumber = "1234567890";
    private BankAccountDAO bankAccountDAO = mock(BankAccountDAO.class);
    private BankAccountService bankAccountService;

    @Before
    public void setup()
    {
        bankAccountService = new BankAccountService();
        bankAccountService.setBankAccountDAO(bankAccountDAO);
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
        assertEquals(bankAccountCaptor.getValue().getAccountNumber(),accountNumber);
        assertEquals(bankAccountCaptor.getValue().getBalance(),0.0);
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
        assertEquals(bankAccountService.getAccount(accountNumber).getAccountNumber(),accountNumber);
        assertEquals(bankAccountService.getAccount(accountNumber).getBalance(),0.0);
    }

    @Test
    public void testDoDeposit() throws Exception
    {
        ArgumentCaptor<BankAccount> bankAccountArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
        when(bankAccountDAO.findByAccountNumber(accountNumber)).thenReturn(new BankAccount(accountNumber,0.0,Calendar.getInstance()));
        bankAccountService.deposit(accountNumber, 500.0,"add 500$");
        verify(bankAccountDAO,times(1)).save(bankAccountArgumentCaptor.capture());

        //add 1st time 500$
        bankAccountService.getAccount(accountNumber);
        assertEquals(500.0, bankAccountArgumentCaptor.getValue().getBalance());

        //add more 2nd time 1000$
        bankAccountService.deposit(accountNumber, 1000.0,"add 1000$");
        verify(bankAccountDAO,times(2)).save(bankAccountArgumentCaptor.capture());
        assertEquals(1500.0, bankAccountArgumentCaptor.getValue().getBalance());

    }

}
