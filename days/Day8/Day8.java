//import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * write a small compiler?
 */
public class Day8 extends Day {
    String input;
    String[] lines;
    Map<String, Integer>[] instructions;
    
    Day8() {
        //inputFile = "days/Day8/test-input8.txt";
        inputFile = "days/Day8/input8.txt";
        input = getInput();
        lines = input.split("\n");
        instructions = new HashMap[lines.length];
    }  

    void getInstructions()
    {
        String op = "";
        int val = 0;
        for (int i = 0; i < lines.length; i++)
        {
            Scanner scanLines = new Scanner(lines[i]);
            op = scanLines.next();
            val = scanLines.nextInt();
            instructions[i] = new HashMap<String, Integer>(Map.of(op, val));
            scanLines.close();
        }
        
    }

    /**
     * Get value in accumulator before it enters the infinite loop
     * 
     * acc=5 with test input
     */
    public void part1() {   
        getInstructions();
        Set<Integer> linesRead = new HashSet<>();
        int acc = 0;
        int i = 0;
        while (i < instructions.length && !linesRead.contains(i))
        {
            linesRead.add(i);
            if (instructions[i].containsKey("nop")) 
            {
                i++;
            }
            else if (instructions[i].containsKey("acc")) 
            {
                acc += instructions[i].get("acc");
                i++;
            }
            else 
            {
                i += instructions[i].get("jmp");
            }
        }
        System.out.printf("Acc=%d\n", acc);
    }


    /**
     * Change one jmp to nop or vice-versa to allow the program to proceed to the next line
     * after the input and get the new acc value
     * 
     * acc=8 with test input
     * 
     */
    public void part2() {
        Deque<Integer> linesRead = new ArrayDeque<>();
        List<Integer> negJmps = new ArrayList<>();
        // find negative (looping) jumps
        for (int m = 0; m < instructions.length; m++)
        {
            if (instructions[m].containsKey("jmp"))
            {
                if (instructions[m].get("jmp") <= 0) negJmps.add(m);
            }
        }
        System.out.println(negJmps);
       
        // test replacing each one with "nop" to find solution
        int acc = 0;
        for (int j = 0; j < negJmps.size(); j++)
        {
            acc = 0;
            int jump = negJmps.get(j);
            int val = instructions[jump].get("jmp");
            instructions[jump] = new HashMap<String, Integer>(Map.of("nop", val));
            if (j > 0)
            {
                val = instructions[negJmps.get(j - 1)].get("nop");
                instructions[negJmps.get(j - 1)] = new HashMap<String, Integer>(Map.of("jmp", val));  
            } 

            int i = 0;
            while (i < instructions.length)
            {
                if (linesRead.contains(i))
                {
                    linesRead.clear();
                    break;
                }
                //System.out.print((i + 1) + " ");
                linesRead.push(i);
                if (instructions[i].containsKey("nop")) 
                {
                    //System.out.println(instructions[i]);
                    i++;
                }
                else if (instructions[i].containsKey("acc")) 
                {
                    //System.out.println(instructions[i]);
                    acc += instructions[i].get("acc");
                    i++;
                }
                else 
                {
                    //System.out.println(instructions[i]);
                    i += instructions[i].get("jmp");
                }
            }
        }
        //System.out.println(linesRead);
        System.out.printf("Acc=%d\n", acc);
    }
}