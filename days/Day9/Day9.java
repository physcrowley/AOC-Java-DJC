import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Day9 extends Day {
    String input;
    int preamble;
    List<Long> nums;
    long[] activeNums;
    List<Long> sumsTable;
    
    Day9() {
        //cache input
        //inputFile = "days/Day9/test-input9.txt";
        inputFile = "days/Day9/input9.txt";
        input = getInput();
        nums = new ArrayList<>();
        Scanner getLines = new Scanner(input);
        while (getLines.hasNext()) 
        {
            nums.add(getLines.nextLong());
            if(getLines.hasNext()) getLines.nextLine();
        }
        getLines.close();
        //System.out.println(nums);

        // establish initial sums
        preamble = 25;
        activeNums = new long[preamble];
        for (int i = 0 ; i < preamble; i++) activeNums[i] = nums.get(i);
        //printActive();
        
        sumsTable = new ArrayList<>();
        for (int i = 1; i < preamble; i++)
        {
            for (int j = i-1; j >= 0; j--) sumsTable.add(activeNums[i] + activeNums[j]);
        }
        //System.out.println(sumsTable);
    }  

    void printActive()
    {
        System.out.print("[");
        for (long n : activeNums) System.out.print(n+", ");
        System.out.print("]\n");
    }

    /**
     * Find first number after a certain size preamble that is not the sum of a pair of numbers in the preamble.
     * As you go down the list, the preamble follows, always being the preamble size of numbers preceding the item in the list
     * 
     * 127 with test input
     */
    public void part1() {
        //simulate stepping forward (remove first and recalculate sums)
        int nextIndex = preamble;

        while (nextIndex < nums.size())
        {
            if (sumsTable.contains(nums.get(nextIndex)))
            {
                // rotate activeNums left
                long replacedVal = activeNums[0];
                for (int i = 1; i < preamble; i++) activeNums[i-1] = activeNums[i];
                activeNums[preamble - 1] = nums.get(nextIndex);
                //printActive();
                
                // recalculate sums
                // TODO optimise by only replacing sums modified by removing and adding numbers
                sumsTable.clear();
                for (int i = 1; i < preamble; i++)
                {
                    for (int j = i-1; j >= 0; j--) sumsTable.add(activeNums[i] + activeNums[j]);
                }
                //System.out.println(sumsTable);
            }
            else 
            {
                System.out.println(nums.get(nextIndex));
                break;
            }
            nextIndex++;
        }    
    }


    /**
     * 
     */
    public void part2() {
        
        System.out.printf("\n");
    }
}