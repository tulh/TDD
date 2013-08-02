import com.tdd.kata4.SingleLinkedList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * User: Tu
 * Date: 8/2/13
 */
public class TestSingleLinkedList
{
    private static SingleLinkedList singleLinkedList;

    public void setUpEmptySingleLinkedList()
    {
        singleLinkedList = new SingleLinkedList();
    }

    public void setUpNotEmptySingleLinkedList()
    {
        List<Object> listObject = new ArrayList<Object>();
        for(int i=0; i<10; i++)
        {
            listObject.add(i);
        }
        singleLinkedList = new SingleLinkedList(listObject);
    }
    @Test
    public void testConstructorWithoutArgument()
    {
        setUpEmptySingleLinkedList();
        assertTrue(singleLinkedList.size == 0);
    }

    @Test
    public void testConstructorWithArgumentIsAnArray()
    {
        setUpNotEmptySingleLinkedList();
        assertTrue(singleLinkedList.size == 10);
    }

    @Test
    public void testAppendMethod()
    {
        setUpEmptySingleLinkedList();
        singleLinkedList.append("a");
    }

}
