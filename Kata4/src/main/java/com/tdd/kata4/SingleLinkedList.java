package com.tdd.kata4;

import java.util.List;

/**
 * User: Tu
 * Date: 8/2/13
 */
public class SingleLinkedList
{
    public int size;
    private Node head;
    private Node tail;

    public SingleLinkedList(List<Object> listObject)
    {
        for(Object e: listObject)
        {
            append(e);
        }
    }

    public SingleLinkedList()
    {
        size = 0;
        head = null;
        tail = null;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public Node getHead()
    {
        return head;
    }

    public void setHead(Node head)
    {
        this.head = head;
    }

    public Node getTail()
    {
        return tail;
    }

    public void setTail(Node tail)
    {
        this.tail = tail;
    }

    public void append(Object data)
    {
        Node newNode = new Node(data, null);
        if(head == null)
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public void insertFirst(Object data)
    {
        Node newNode = new Node(data, head);
        if(head == null)
        {
            newNode.setNext(null);
            head = newNode;
            tail = newNode;
        }
        else
        {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    public void insertAfter(Node n, Object e)
    {
        if(n == null)
            insertFirst(e);
        else if(n == tail)
            append(e);
        else {
            Node newNode = new Node(e,n.getNext());
            n.setNext(newNode);
        }
    }

    public int find(Object o)
    {
        Node current = head;
        int index = 0;
        while(current != null)
        {
            if(current.getE().equals(o))
            {
                return index;
            }
            else
            {
                index++;
                current = current.getNext();
            }
        }
        return -1;
    }

    public Node getAt(int index)
    {
        if(index < 0)
            return null;
        else if(index == 0)
            return head;
        else
        {
            Node current = head;
            int i=0;
            while(current!=null && i<index)
            {
                i++;
                current = current.getNext();
            }
            return current;
        }
    }

    public void delete(Node at)
    {
        at.setE(at.getNext().getE());
        at.setNext(at.getNext());
        size--;
    }

    public Node before(Node at)
    {
        int currentIndex = find(at.getE());
        if(currentIndex > 0)
        {
            return getAt(currentIndex-1);
        }
        else return null;
    }

    public Node after(Node at)
    {
        int currentIndex = find(at.getE());
        if(currentIndex < size)
        {
            return getAt(currentIndex+1);
        }
        else return null;
    }

    public Node first()
    {
        if(size > 0)
            return getAt(0);
        else return null;
    }

    public Node last()
    {
        if(size > 0)
            return getAt(size-1);
        else return null;
    }
}
