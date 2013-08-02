import com.tdd.kata4.SingleLinkedList;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * User: Tu
 * Date: 8/2/13
 */
public class TestSingleLinkedList
{
    @Test
    public void testConstructorWithoutArgument()
    {
        SingleLinkedList list = new SingleLinkedList();
        assertTrue(list.size == 0);
    }

}
