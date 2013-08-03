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
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void insertFirst(Object data)
    {
        Node newNode = new Node(data, head);
        if(head == null)
        {
            newNode.next = null;
            head = newNode;
            tail = newNode;
        }
        else
        {
            newNode.next = head;
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
            Node newNode = new Node(e,n.next);
            n.next = newNode;
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
                current = current.next;
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
                current = current.next;
            }
            return current;
        }
    }

    public void delete(Node at)
    {
        at.e = at.next.e;
        at.next = at.next.next;
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

    public static class Node
    {
        private Node next;
        private Object e;

        public Node(Object e, Node next)
        {
            this.e  = e;
            this.next = next;
        }

        public Node getNext()
        {
            return next;
        }

        public void setNext(Node next)
        {
            this.next = next;
        }

        public Object getE()
        {
            return e;
        }

        public void setE(Object e)
        {
            this.e = e;
        }
    }
}
