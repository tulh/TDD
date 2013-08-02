import com.tdd.kata4.SingleLinkedList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
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
    public void testCreateEmptyLinkedList()
    {
        setUpEmptySingleLinkedList();
        assertTrue(singleLinkedList.size == 0);
    }

    @Test
    public void testCreateLinkedListFromAnArray()
    {
        setUpNotEmptySingleLinkedList();
        assertTrue(singleLinkedList.size == 10);
    }

    @Test
    public void testAppendMethod()
    {
        setUpEmptySingleLinkedList();
        // at first singleLinkedList is empty
        assertTrue(singleLinkedList.size == 0);
        // now it has 1 node
        singleLinkedList.append("a");
        assertTrue(singleLinkedList.size == 1);
    }

    @Test
    public void testInsertFirstMethod()
    {
        setUpEmptySingleLinkedList();
        // 1st time list is empty
        assertTrue(singleLinkedList.size == 0);
        // after insert at first
        singleLinkedList.insertFirst("a");
        assertEquals(1,singleLinkedList.size);
    }

}
