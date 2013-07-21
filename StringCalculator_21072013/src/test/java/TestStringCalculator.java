import org.junit.Test;

/**
 * User: Tu
 * Date: 7/22/13
 */import static org.junit.Assert.assertTrue;

public class TestStringCalculator
{
    @Test
    public void testWithInputBlankString() throws Exception
    {
        assertTrue(StringCalculator.add("")==0);

    }
}
