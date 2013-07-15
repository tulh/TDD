import org.junit.Before;
import org.junit.Test;
import tdd.kata.StringCalculator;

/**
 * User: Tu
 * Date: 7/11/13
 */
import static org.junit.Assert.*;
public class StringCalculatorTest
{
    private StringCalculator stringCalculator;

    @Before
    public void createInstance()  {
        stringCalculator = new StringCalculator();
    }
    @Test
    public void testWithBlankString() throws Exception
    {
        assertEquals(0,stringCalculator.add(""));

    }

    @Test
    public void testWithOneNumber() throws Exception
    {
        assertEquals(1, stringCalculator.add("1"));
    }
    @Test
    public void testWithTwoNumber() throws Exception
    {
        assertEquals(3,stringCalculator.add("1,2"));
    }
    @Test
    public void testWithMultipleNumber() throws Exception
    {
        assertEquals(10,stringCalculator.add("3,3,3,1"));
    }
    @Test
    public void testWithNewlineBetweenNumbers() throws Exception
    {
        assertEquals(6,stringCalculator.add("1\n2,3"));
    }
    @Test
    public void testWithCustomDelimiter()throws Exception
    {
        assertEquals(3,stringCalculator.add("//;\n1;2"));

    }
    @Test
    public void testWithNegativeNumber()throws Exception
    {
        try {
            stringCalculator.add("-1,-2,3");
            fail("Exception with negative number");
        }
        catch (Exception e){
            assertEquals("negatives not allowed:[-1, -2]",e.getMessage());
        }
    }
    @Test
    public void testWithNumberGreaterThan1000() throws Exception
    {
        assertTrue(stringCalculator.add("1001,1")==1);
    }
    @Test
    public void testWithDelimiterWithLengthUnpredicted() throws Exception
    {
        assertEquals(stringCalculator.add("//[***]\n1***2***3"),6);
    }
    @Test
    public void testWithMultipleDelimiter()throws Exception
    {
        assertEquals(stringCalculator.add("//[*][%]\n1*2%3"),6);
    }

}
