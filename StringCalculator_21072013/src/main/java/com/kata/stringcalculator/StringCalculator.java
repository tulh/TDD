package com.kata.stringcalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Tu
 * Date: 7/21/13
 */
public class StringCalculator
{
    private static final String DEFAULT_DELIMITER = ",|\n";
    private static final String DELIMITER_PATTERN = "//(.)\n(.*)";
    private static final String CUSTOMIZED_DELIMITER_INDICATOR = "//";
    private static final Integer MAX_VALUE = 1000;

    public int add(String numbers) throws Exception
    {
        if (numbers.isEmpty())
        {
            return 0;
        }
        else
        {
            String[] arrayNumber = tokenizer(numbers);
            List<Integer> negativeNumber = new ArrayList<Integer>();
            int total = 0;
            int temp;
            for (int i = 0; i < arrayNumber.length; i++)
            {
                temp = string2IntConverter(arrayNumber[i]);
                if (temp < 0)
                {
                    negativeNumber.add(temp);
                }
                else if (temp > MAX_VALUE)
                {
                    //do nothing
                }
                else
                {
                    total += temp;
                }
            }
            if (negativeNumber.size() > 0)
            {
                throw new Exception("negatives not allowed " + listToString(negativeNumber));
            }
            return total;
        }
    }

    private String listToString(List<Integer> source)
    {
        String target = "";
        for (Integer i : source)
        {
            target += i + " ";
        }
        return target;
    }

    private int string2IntConverter(String numbers) throws NumberFormatException
    {
        return Integer.parseInt(numbers);
    }

    private String[] tokenizer(String numbers)
    {
        if (numbers.startsWith(CUSTOMIZED_DELIMITER_INDICATOR))
        {
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
