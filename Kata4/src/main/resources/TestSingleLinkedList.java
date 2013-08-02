import com.tdd.kata4.SingleLinkedList;
import org.junit.Test;

/**
 * User: Tu
 * Date: 8/2/13
 */
public class TestSingleLinkedList
{
    @Test
    public void testConstructorWithoutArgument()
    {
        SingleLinkedList list = new StringleLinkedList();
        assertTrue(list.size == 0);
    }

}
