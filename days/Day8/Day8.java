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
            instructions[i] = new HashMap<>(Map.of(op, val));
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
        int stackSizeOfChange = instructions.length;
        int acc = 0;
        int i = 0;
        while (i < instructions.length)
        {
            if (linesRead.contains(i))
            { 
                while (true) 
                {
                    i = linesRead.pop();
                    if (instructions[i].containsKey("acc")){
                        acc -= instructions[i].get("acc");
                    }
                    else if (instructions[i].containsKey("nop"))
                    {
                        if (linesRead.size() <= stackSizeOfChange + 1)
                        {
                            int val = instructions[i].get("nop");
                            instructions[i] = new HashMap<>(Map.of("jmp", val));
                        }
                        if (linesRead.size() < stackSizeOfChange + 1) break;
                    }
                    else if (instructions[i].containsKey("jmp"))
                    {
                        if (linesRead.size() <= stackSizeOfChange + 1)
                        {
                            int val = instructions[i].get("jmp");
                            instructions[i] = new HashMap<>(Map.of("nop", val));
                        }
                        if (linesRead.size() < stackSizeOfChange + 1) break;
                    }
                }
                stackSizeOfChange = i;
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
        //System.out.println(linesRead);
        System.out.printf("Acc=%d\n", acc);
    }
}