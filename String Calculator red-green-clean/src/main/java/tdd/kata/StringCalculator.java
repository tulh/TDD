package tdd.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Tu
 * Date: 7/11/13
 */
public class StringCalculator
{
    public static final String DEFAULT_DELIMITERS = ",|\n";
    public static final String DELIMITER_PATTERN = "//((.)|(\\[.*\\])*)\\n";
    public static final int MAX_VALUE = 1000;

    public int add(String numbers) throws Exception
    {
        if(numbers.isEmpty())
            return 0;
        else {
            String[] number = tokenizer(numbers);
            int total = 0;
            int temp;
            List<Integer> negativeNumber = new ArrayList<Integer>();
            for(int i=0; i< number.length; i++){
                temp = convertString2Int(number[i]);
                if (temp < 0)
                {
                    negativeNumber.add(temp);
                }
                else if(temp > MAX_VALUE) {
                }
                else total += temp;
            }
            if(negativeNumber.size()>0) {
                throw new Exception("negatives not allowed:" + negativeNumber.toString());
            }
            return total;
        }

    }

    private String[] tokenizer(String numbers)
    {
        if(numbers.startsWith("//")) {
            return splitNumbersWithCustomDelimiter(numbers);
        }
        return numbers.split(DEFAULT_DELIMITERS);
    }
    private String[] splitNumbersWithCustomDelimiter(String numbers)
    {
        Pattern pattern = Pattern.compile(DELIMITER_PATTERN);
        Matcher m = pattern.matcher(numbers);
        String delimiters ="";
        StringBuilder tString = new StringBuilder(numbers);
        String[] arrDelimiters = new String[0];
        if (m.find())
        {
            tString.delete(0, tString.length());
            tString.append(numbers.replace(m.group(0), ""));
            delimiters = m.group(1);
            if (delimiters.length() > 1)
            {
                delimiters = delimiters.substring(1, delimiters.length() - 1);
            }
            arrDelimiters = delimiters.split("\\]\\[");
        }
        String result = "";
        for(int i=0; i< arrDelimiters.length;i++)
        {
            result += Pattern.quote(arrDelimiters[i]) + "|";
        }
        return tString.toString().split(result.substring(0,result.length()-1));
    }
    private int convertString2Int(String number)
    {
        return Integer.parseInt(number);
    }
}
