import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * User: Tu
 * Date: 7/4/13
 */
public class TestStringCalculator
{
    StringCalculator stringCalculator;

    @Before
    public void setUp()
    {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void testWithBlankString() throws Exception
    {
        int result = stringCalculator.add("");
        Assert.assertEquals(result, 0);
    }

    @Test
    public void testWithOneNumber() throws Exception
    {
        int result = stringCalculator.add("3");
        Assert.assertEquals(result, 3);
    }

    @Test
    public void testWithLotsOfNumber() throws Exception
    {
        int result = stringCalculator.add("1,2,3,4,5,6");
        Assert.assertFalse(result == 25);
    }

    @Test
    public void testWithNewLinesBetweenNumbers() throws Exception
    {
        int result = stringCalculator.add("1\n2,3");
        Assert.assertTrue(result == 6);
    }

    @Test(expected = NumberFormatException.class)
    public void testWithInvalidNewLinesInput() throws Exception
    {
        int result = stringCalculator.add("1,\n,2,3");
        Assert.fail();
    }

    @Test
    public void testWithNewDelimiter() throws Exception
    {
        int result = stringCalculator.add("//;1;2");
        Assert.assertTrue(result == 3);
    }

    @Test(expected = Exception.class)
    public void testWithNegativeNumber() throws Exception
    {
        int result = stringCalculator.add("2;-3;4");
        Assert.fail();

    }
    @Test
    public void testWithNumberGreaterThan1000() throws Exception
    {
        int result = stringCalculator.add("1000;3;5");
        Assert.assertTrue(result==8);
    }

    @Test
    public void testWithComplicatedDelimiters() throws Exception
    {
        int result = stringCalculator.add("//[***]\n1***2***3");
        Assert.assertTrue(result==6);

    }
}
