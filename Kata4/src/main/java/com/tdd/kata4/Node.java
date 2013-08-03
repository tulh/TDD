package com.tdd.kata4;

/**
 * User: Tu
 * Date: 8/3/13
 */
public class Node
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
