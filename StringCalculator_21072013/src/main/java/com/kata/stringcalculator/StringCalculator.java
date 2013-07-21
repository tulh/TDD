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
            String [] arrayNumber = numbers.split(",");
            int total = 0;
            for(int i=0; i<arrayNumber.length; i++)
            {
                total += string2IntConverter(arrayNumber[i]);

            }
            return total;
        }
    }

    private int string2IntConverter(String numbers)
    {
        return Integer.parseInt(numbers);
    }

}
