import com.kata.stringcalculator.StringCalculator;
import org.junit.Before;
import org.junit.Test;

/**
 * User: Tu
 * Date: 7/22/13
 */
import static org.junit.Assert.assertTrue;

public class TestStringCalculator
{
    private StringCalculator stringCalculator;
    @Before
    public void createNewInstance()
    {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void testWithInputBlankString() throws Exception
    {
        assertTrue(stringCalculator.add("")==0);

    }
}
