import com.kata.stringcalculator.StringCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User: Tu
 * Date: 7/22/13
 */

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

    @Test (expected = NumberFormatException.class)
    public void testWithInvalidInput() throws Exception
    {
        stringCalculator.add("1n,\n");
        fail("For input string: \"1n\"");
    }

    @Test
    public void testWithCustomDelimiter() throws Exception
    {
        assertEquals(stringCalculator.add("//;\n1;2"),3);
    }
}
