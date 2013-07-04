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
    public void testWithBlankString()
    {
        int result = stringCalculator.add("");
        Assert.assertEquals(result, 0);
    }

    @Test
    public void testWithOneNumber()
    {
        int result = stringCalculator.add("3");
        Assert.assertEquals(result, 3);
    }

    @Test
    public void testWithLotsOfNumber()
    {
        int result = stringCalculator.add("1,2,3,4,5,6");
        Assert.assertFalse(result == 25);
    }

    @Test
    public void testWithNewLinesBetweenNumbers()
    {
        int result = stringCalculator.add("1\n2,3");
        Assert.assertTrue(result == 6);
    }

    @Test(expected = NumberFormatException.class)
    public void testWithInvalidNewLinesInput()
    {
        int result = stringCalculator.add("1,\n,2,3");
        Assert.fail();
    }

    @Test
    public void testWithNewDelimiter()
    {
        int result = stringCalculator.add("//;1;2");
        Assert.assertTrue(result == 3);
    }

}
