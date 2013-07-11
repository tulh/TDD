package tdd.kata;

/**
 * User: Tu
 * Date: 7/11/13
 */
public class StringCalculator
{
    public static String DELIMITERS = ",|\n";
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
        return numbers.split(DELIMITERS);
    }

    private int convertString2Int(String number)
    {
        return Integer.parseInt(number);
    }
}
