import com.kata.bankaccount.BankAccount;
import com.kata.bankaccount.dao.BankAccountDAO;
import com.kata.bankaccount.service.BankAccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

    }
}
