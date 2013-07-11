package tdd.kata;

/**
 * User: Tu
 * Date: 7/11/13
 */
public class StringCalculator
{
    public int add(String numbers){
        if(numbers.trim().equals(""))
            return 0;
        else{
            if(numbers.contains(",")){
                String []number = numbers.split(",");
                int total = 0;
                for(int i=0; i< number.length; i++){
                    total +=Integer.parseInt(number[i]);
                }
                return total;
            }
            else return Integer.parseInt(numbers);
        }

    }
}
