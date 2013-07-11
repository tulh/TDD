package tdd.kata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Tu
 * Date: 7/11/13
 */
public class StringCalculator
{
    public static String DEFAULT_DELIMITERS = ",|\n";
    public int add(String numbers){
        if(numbers.isEmpty())
            return 0;
        else {
            String[] number = tokenizer(numbers);
            int total = 0;
            for(int i=0; i< number.length; i++){
                total += convertString2Int(number[i]);
            }
            return total;
        }

    }

    private String[] tokenizer(String numbers)
    {
        if(numbers.startsWith("//")) {
            Pattern pattern = Pattern.compile("//(.)\n(.*)");
            Matcher m = pattern.matcher(numbers);
            if(m.matches()) {
                String delimiter = m.group(1);
                String number = m.group(2);
                return number.split(delimiter);
            }
        }
         else return numbers.split(DEFAULT_DELIMITERS);
    }

    private int convertString2Int(String number)
    {
        return Integer.parseInt(number);
    }
}
