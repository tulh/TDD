import com.kata.stringcalculator.StringCalculator;
import org.junit.Before;
import org.junit.Test;

/**
 * User: Tu
 * Date: 7/22/13
 */
import static org.junit.Assert.assertEquals;
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

    @Test
    public void testWithInputOneNumber() throws Exception
    {
        assertEquals(stringCalculator.add("1"),1);
    }

    @Test
    public void testWithInputTwoNumbers() throws Exception
    {
        assertEquals(stringCalculator.add("1,2"),3);
    }

    @Test
    public void testWithNewLineBetweenNumbers() throws Exception
    {
        assertEquals(stringCalculator.add("1\n2,3"),6);
    }
}
