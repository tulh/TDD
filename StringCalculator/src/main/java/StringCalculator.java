/**
 * User: Tu
 * Date: 7/3/13
 */
public class StringCalculator
{
    public int add(String numbers)
    {
        if(numbers.equals(""))
            return 0;
        else {
            if(numbers.length()>1)
            {
                String [] arrays = numbers.split(",");
                int length = arrays.length;
                int total=0;
                for(int i=0;i<length;i++) {
                    total += Integer.parseInt(arrays[i]);
                }
                return total;
            }
            else return Integer.parseInt(numbers);
        }

    }
}
