package com.kata.stringcalculator;

/**
 * User: Tu
 * Date: 7/22/13
 */
public class StringCalculator
{
    public int add(String numbers)
    {
        if(numbers.isEmpty())
        {
            return 0;
        }
        else
        {
            return string2IntConverter(numbers);
        }
    }

    private int string2IntConverter(String numbers)
    {
        return Integer.parseInt(numbers);
    }

}
