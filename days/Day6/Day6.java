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
        Set<Character> choices = new HashSet<>();
        Set<Character> thisChoices = new HashSet<>();
        int sumChoices = 0;
        boolean first = true;
        for (int j = 0; j < 6; j++)
        {
            if (lines[j].equals("")) 
            {
                sumChoices += choices.size();
                //System.out.println(choices);
                choices.clear();
                first = true;
            }
            else
            {
                for (int i = 0; i < lines[j].length(); i++)
                {
                    if (first) choices.add(lines[j].charAt(i)); // add entries for first member
                    else thisChoices.add(lines[j].charAt(i)); // for other members
                }
                if (first) thisChoices = choices;
                first = false;
                System.out.println("thisChoices = " + thisChoices);
            }
            
            //System.out.println(choices);
            //System.out.println(thisChoices);
            System.out.println(thisChoices.retainAll(choices));
            System.out.println("after retainAll =" + thisChoices + choices);
            thisChoices.clear();
        }

        System.out.printf("Total unique=%d\n", sumChoices);
    }
}