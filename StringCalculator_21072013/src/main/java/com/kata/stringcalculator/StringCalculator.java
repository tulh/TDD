package com.kata.stringcalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Tu
 * Date: 7/22/13
 */
public class StringCalculator
{
    private static final String DEFAULT_DELIMITER = ",|\n";
    private static final String DELIMITER_PATTERN = "//(.)\n(.*)";
    public int add(String numbers)
    {
        if (numbers.isEmpty())
        {
            return 0;
        }
        else
        {
            String[] arrayNumber = tokenizer(numbers);
            int total = 0;
            for (int i = 0; i < arrayNumber.length; i++)
            {
                total += string2IntConverter(arrayNumber[i]);
            }
            return total;
        }
    }

    private int string2IntConverter(String numbers) throws NumberFormatException
    {
        return Integer.parseInt(numbers);
    }

    private String[] tokenizer(String numbers)
    {
        if(numbers.startsWith("//")) {
            Pattern p = Pattern.compile(DELIMITER_PATTERN);
            Matcher m = p.matcher(numbers);
            m.matches();
            String delimiter = m.group(1);
            String number = m.group(2);
            return number.split(delimiter);
        }
        return numbers.split(DEFAULT_DELIMITER);
    }
}
