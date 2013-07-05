/**
 * User: Tu
 * Date: 8/4/13
 */
public class StringCalculator
{
    public static final String DELIMITERS = "(,|\n|;|\\*|\\]|\\[|\\/)";
    public int add(String numbers) throws Exception
    {
        if (numbers.trim().equals(""))
        {
            return 0;
        }
        else
        {
            if (numbers.length() > 1)
            {
                String[] arrays = numbers.split(DELIMITERS);
                int length = arrays.length;
                int total = 0;
                for (int i = 0; i < length; i++)
                {
                    if(arrays[i].contains("-"))
                    {
                        throw new Exception("Negatives not allowed " + arrays[i]);
                    }
                    else {
                        int temp = Integer.parseInt(arrays[i]);
                        if(temp < 1000)
                        {
                            try
                            {
                                total += temp;
                            }
                            catch (NumberFormatException e)
                            {
                                throw new NumberFormatException("Invalid number format");
                            }
                        }
                    }
                }
                return total;
            }
            else
            {
                return Integer.parseInt(numbers);
            }
        }

    }
}
