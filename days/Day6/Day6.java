import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

import javax.lang.model.util.ElementScanner14;

public class Day6 extends Day {
    String input;
    String[] groups;
    String[] lines;
    
    Day6() {
        inputFile = "days/Day6/input6.txt";
        input = getInput();
        groups = input.split("\n\n"); // groups separated by blank lines
        lines = input.split("\n"); // groups separated by empty entries
    }

    /**
     * 
     */
    public void part1() {   
        Map<Character, Character> choices = new HashMap<>();
        int sumChoices = 0;
        for (int j = 0; j < groups.length; j++)
        {
            choices.clear();
            groups[j] = groups[j].replaceAll("[ \n]+", "");
            for (int i = 0; i < groups[j].length(); i++)
            {
                choices.put(groups[j].charAt(i), groups[j].charAt(i));
            }
            sumChoices += choices.size();
        }

        System.out.printf("Total unique=%d\n", sumChoices);
    }

    /**
     * 
     */
    public void part2() {
        Map<Character, Integer> choices = new HashMap<>();
        int members = 0;
        int sumChoices = 0;

        for (int i = 0; i < lines.length; i++)
        {
            if (lines[i].equals(""))
            {
                for (char c : choices.keySet())
                {
                    if (choices.get(c) == members) 
                    {
                        sumChoices++;
                    }     
                }
                System.out.println(members);
                System.out.println(choices);
                System.out.println(sumChoices);
                choices.clear();
                members = 0;
            }
            else
            {
                members++;
                for (char c : lines[i].toCharArray())
                {
                    if (choices.containsKey(c)) choices.put(c, choices.get(c)+1);
                    else choices.put(c, 1);
                }
            }
        }

        System.out.printf("Total unique=%d\n", sumChoices);
    }
}