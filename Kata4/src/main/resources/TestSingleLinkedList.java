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
        assertEquals(1, singleLinkedList.size);
    }

    @Test
    public void testFindFirstNodeContainObject()
    {
        setUpNotEmptySingleLinkedList();
        // find object e=3 in the list, should return 3
        assertEquals(3, singleLinkedList.find(3));
        // find object = 11 not in the list, should return -1
        assertEquals(-1, singleLinkedList.find(11));
    }

    @Test
    public void testInsertAfter()
    {
        setUpEmptySingleLinkedList();
        singleLinkedList.append('a');
        singleLinkedList.append('b');
        singleLinkedList.append('c');
        //before doing insert c is at index = 2
        assertEquals('c',singleLinkedList.getAt(2).getE());
        singleLinkedList.insertAfter(singleLinkedList.getAt(1),3);
        // after doing insert c is at index = 3
        assertEquals('c', singleLinkedList.getAt(3).getE());
    }

    @Test
    public void testDeleteNodeFromLinkedList()
    {
        setUpNotEmptySingleLinkedList();
        assertTrue(singleLinkedList.size == 10);
        assertEquals(3, singleLinkedList.getAt(3).getE());
        singleLinkedList.delete(singleLinkedList.getAt(3));
        assertEquals(9,singleLinkedList.size);
        assertEquals(4,singleLinkedList.getAt(3).getE());
    }

    @Test
    public void testBefore()
    {
        setUpNotEmptySingleLinkedList();
        System.out.println(singleLinkedList.getAt(3).getE());
        // expect return node 2 has value = 2 before node 3 has value = 3
        assertEquals(2,singleLinkedList.before(singleLinkedList.getAt(3)).getE());
    }

    @Test
    public void testAfter()
    {
        setUpNotEmptySingleLinkedList();
        System.out.println(singleLinkedList.getAt(3).getE());
        // expect return node 4 has value = 4 after node 3 has value = 3
        assertEquals(4, singleLinkedList.after(singleLinkedList.getAt(3)).getE());
    }
}
