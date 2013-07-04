/**
 * User: Tu
 * Date: 8/4/13
 */
public class StringCalculator
{
    public int add(String numbers)
    {
        if (numbers.trim().equals(""))
        {
            return 0;
        }
        else
        {
            if (numbers.length() > 1)
            {
                String delimiters = ",|\\n";
                if (numbers.startsWith("//"))
                {
                    delimiters = numbers.substring(2, 3);
                    numbers = numbers.substring(3);
                }
                String[] arrays = numbers.split(delimiters);
                int length = arrays.length;
                int total = 0;
                for (int i = 0; i < length; i++)
                {
                    try
                    {

                        total += Integer.parseInt(arrays[i]);
                    }
                    catch (NumberFormatException e)
                    {
                        throw new NumberFormatException("Invalid number format");
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
