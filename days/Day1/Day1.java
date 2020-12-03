import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day1 extends Day {
    Day1() {
        inputFile = "days/Day1/input1.txt";
    }

    /**
     * find two numbers that sum to 2020 and calculate their product
     */
    public void part1() {
        List<Integer> nums = new ArrayList<Integer>();
        //List<Integer> nums = new LinkedList<Integer>();

        String input = this.getInput();
        for (String line : input.split("\n")) {
            nums.add(Integer.parseInt(line));
        }

        int num1 = 0, num2 = 0;
        for (int j = 0; j < nums.size() - 1; j++)
        {
            num1 = nums.get(j);
            for (int i = 1; i < nums.size(); i++)
            {
                if (num1 + nums.get(i) == 2020)
                {
                    num2 = nums.get(i);
                    break;
                }
            }
            if (num2 != 0) break;
        }
        System.out.printf("Numbers: %d, %d, Product: %d\n", num1, num2, num1 * num2);
    }

    /**
     * find three numbers that sum to 2020 and calculate their product
     */
    public void part2() {
        List<Integer> nums = new ArrayList<Integer>();
        //List<Integer> nums = new LinkedList<Integer>();

        String input = this.getInput();
        for (String line : input.split("\n")) {
            nums.add(Integer.parseInt(line));
        }

        int num1 = 0, num2 = 0, num3 = 0;
        for (int j = 0; j < nums.size() - 2; j++)
        {
            num1 = nums.get(j);
            for (int i = 1; i < nums.size() - 1; i++)
            {
                num2 = nums.get(i);
                for (int k = 2; k < nums.size(); k++)
                {
                    if (num2 + nums.get(k) == 2020 - num1)
                    {
                        num3 = nums.get(k);
                        break;
                    }
                }
                if (num3 != 0) break; // better to use while loops?
            }
            if (num3 != 0) break;
        }
        System.out.printf("Numbers: %d, %d, %d Product: %d\n", num1, num2, num3, num1 * num2 * num3);
    }
}