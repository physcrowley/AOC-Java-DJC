import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * write a small compiler?
 */
public class Day8 extends Day {
    String input;
    String[] lines;
    Map<K, V> instructions;
    
    Day8() {
        inputFile = "days/Day8/test-input8.txt";
        //inputFile = "days/Day8/input8.txt";
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
        Set<Integer> linesRead = new HashSet<>();
        Deque<Map<Integer, Integer>> jmpNopStack = new ArrayDeque<>();
        int acc = 0;
        int i = 0;
        int lastJmp = 0;
        int lastChange = 0;
        while (i < instructions.length)
        {
            if (linesRead.contains(i))
            { 
                if (jmpNopStack.size() > 0 ) i = jmpNopStack.pop();
                else{
                    System.out.println("fail: no changes to fix");
                    break;
                }
                
                int j = -1;
                while (j != lastChange)
                {
                    j = jmpNopStack.pop();
                    if (instructions[i].containsKey("jmp")) 
                    {
                        int val = instructions[i].get("jmp");
                        instructions[i] = new HashMap<>(Map.of("nop", val));
                    }
                    else //nop 
                    {
                        int val = instructions[i].get("nop");
                        instructions[i] = new HashMap<>(Map.of("jmp", val));
                    }
                }
            }
            linesRead.add(i);
            if (instructions[i].containsKey("nop")) 
            {
                jmpNopStack.push(new HashMap<>(Map.of(i, acc)));
                i++;
            }
            else if (instructions[i].containsKey("acc")) 
            {
                acc += instructions[i].get("acc");
                i++;
            }
            else 
            {
                jmpNopStack.push(new HashMap<>(Map.of(i, acc)));
                lastJmp = -instructions[i].get("jmp");
                i += lastJmp;
            }
        }
        System.out.printf("Acc=%d\n", acc);
    }
}