import junit.framework.Assert;
import org.junit.Test;

/**
 * User: Tu
 * Date: 7/3/13
 */
public class TestStringCalculator
{
    StringCalculator stringCalculator;

    @Test
    public void testWithBlankString()
    {   stringCalculator = new StringCalculator();
        int result = stringCalculator.add("");
        Assert.assertEquals(result,0);
    }

    @Test
    public void testWithOneNumber()
    {
        stringCalculator = new StringCalculator();
        int result = stringCalculator.add("3");
        Assert.assertEquals(result,3);
    }

    @Test
    public void testWithLotsOfNumber()
    {
        stringCalculator = new StringCalculator();
        int result = stringCalculator.add("1,3,5,7,9");
        Assert.assertTrue(result==25);
    }

}
